package com.example.skindemo.skin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.view.LayoutInflaterFactory;

import java.util.ArrayList;
import java.util.List;

public class SkinFactory implements LayoutInflaterFactory {

    private List<SkinItem> skinItems = new ArrayList<>();

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createView(name, context, attrs);
        if (view != null) {
            collectViewAttr(view, context, attrs);
        }
        return view;
    }

    private View createView(String name, Context context, AttributeSet attrs) {
        View view = null;
        try {
            if (-1 == name.indexOf('.')) {    //不带".",说明是系统的View
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }
            } else {    //带".",说明是自定义的View
                view = LayoutInflater.from(context).createView(name, null, attrs);
            }
        } catch (Exception e) {
            view = null;
        }
        return view;
    }

    private void collectViewAttr(View view, Context context, AttributeSet attrs) {
        List<SkinAttr> skinAttrs = new ArrayList<>();
        int attCount = attrs.getAttributeCount();
        for (int i = 0; i < attCount; ++i) {
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            if (isSupportedAttr(attributeName)) {
                if (attributeValue.startsWith("@")) {
                    int resId = Integer.parseInt(attributeValue.substring(1));
                    String resName = context.getResources().getResourceEntryName(resId);
                    String attrType = context.getResources().getResourceTypeName(resId);
                    skinAttrs.add(new SkinAttr(attributeName, attrType, resName, resId));
                    SkinItem skinItem = new SkinItem(view, skinAttrs);
                    skinItem.apply();
                    skinItems.add(skinItem);
                }
            }
        }
    }

    private boolean isSupportedAttr(String attributeName) {
        return "background".equals(attributeName) || "textColor".equals(attributeName);
    }

    public void apply() {
        for (SkinItem item : skinItems) {
            item.apply();
        }
    }
}
