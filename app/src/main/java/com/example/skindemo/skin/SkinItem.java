package com.example.skindemo.skin;

import android.view.View;
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
        if (view == null || attrs == null)
            return;
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
            }
        }
    }

}
