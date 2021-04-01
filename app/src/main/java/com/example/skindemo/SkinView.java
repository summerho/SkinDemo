package com.example.skindemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class SkinView extends LinearLayout {

    public SkinView(Context context) {
        super(context);
        intView(context);
    }

    public SkinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intView(context);
    }

    private void intView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.skin_view_layout, this, true);
    }
}
