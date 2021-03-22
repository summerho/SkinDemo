package com.example.skindemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SkinView extends LinearLayout {

    private LinearLayout mRootView;
    private TextView mTv1;
    private TextView mTv2;

    public SkinView(Context context) {
        super(context);
    }

    public SkinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intView(context);
    }

    private void intView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.skin_view_layout, this, true);
        mRootView = findViewById(R.id.root_view);
        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
        changeSkin();
    }

    public void changeSkin() {
//        mRootView.setBackgroundColor(getResources().getColor(R.color.root_view_bg_color));
        mTv1.setBackgroundColor(getResources().getColor(R.color.bang_bg_color));
        mTv2.setBackgroundColor(getResources().getColor(R.color.bang_bg_color));
        mTv1.setTextColor(getResources().getColor(R.color.text_color));
        mTv2.setTextColor(getResources().getColor(R.color.text_color));
    }
}
