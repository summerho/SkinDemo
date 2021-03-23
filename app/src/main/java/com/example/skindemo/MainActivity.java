package com.example.skindemo;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.skindemo.skin.SkinConfigManager;

public class MainActivity extends BaseActivity {

    private RelativeLayout mRootView;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private SkinView mSkinView;
    private TextView mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRootView = findViewById(R.id.root_view);
        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
        mTv3 = findViewById(R.id.tv3);
        mSkinView = findViewById(R.id.skin_view);
        mBtn = findViewById(R.id.change_skin_btn);
        mBtn.setOnClickListener(view -> SkinConfigManager.getInstance().changeSkin());
    }
}