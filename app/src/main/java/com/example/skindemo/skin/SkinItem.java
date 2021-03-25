package com.example.skindemo.skin;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SkinItem {

    private View view;

    private List<SkinAttr> attrs;

    public SkinItem(View view, List<SkinAttr> attrs) {
        this.view = view;
        this.attrs = attrs;
    }

    public void apply() {
        if (view == null || attrs == null) {
            return;
        }
        Drawable left = null;
        Drawable right = null;
        Drawable top = null;
        Drawable bottom = null;
        for (SkinAttr attr : attrs) {
            String attrName = attr.getAttrName();
            String attrType = attr.getAttrType();
            String resName = attr.getResName();
            int resId = attr.getResId();
            if ("background".equals(attrName)) {
                if ("color".equals(attrType)) {
                    view.setBackgroundColor(SkinConfigManager.getInstance().getSkinResource().getColor(resId));
                } else if ("drawable".equals(attrType)) {
                    view.setBackground(SkinConfigManager.getInstance().getSkinResource().getDrawable(resId));
                }
            } else if ("textColor".equals(attrName)) {
                if (view instanceof TextView && "color".equals(attrType)) {
                    ((TextView) view).setTextColor(SkinConfigManager.getInstance().getSkinResource().getColor(resId));
                }
            } else if ("src".equals(attrName)) {
                if (view instanceof ImageView && "drawable".equals(attrType)) {
                    ((ImageView) view).setImageDrawable(SkinConfigManager.getInstance().getSkinResource().getDrawable(resId));
                }
            } else if ("drawableLeft".equals(attrName) || "drawableStart".equals(attrName)) {
                left = SkinConfigManager.getInstance().getSkinResource().getDrawable(resId);
            } else if ("drawableRight".equals(attrName) || "drawableEnd".equals(attrName)) {
                right = SkinConfigManager.getInstance().getSkinResource().getDrawable(resId);
            } else if ("drawableTop".equals(attrName)) {
                top = SkinConfigManager.getInstance().getSkinResource().getDrawable(resId);
            } else if ("drawableBottom".equals(attrName)) {
                bottom = SkinConfigManager.getInstance().getSkinResource().getDrawable(resId);
            }
        }
        if ((left != null || right != null || top != null || bottom != null) && view instanceof TextView) {
            ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

}
