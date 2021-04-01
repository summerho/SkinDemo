package com.example.skindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skindemo.skin.SkinConfigManager;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_layout);
        Fragment secondFragment = new SecondFragment();
        addFragment(R.id.container, secondFragment, "def");
        TextView tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(view -> SkinConfigManager.getInstance().changeSkin());
        FrameLayout container = findViewById(R.id.container);
        View childView = new SkinView(this);
        TextView tv2 = findViewById(R.id.tv2);
        tv2.setOnClickListener(view -> {
            if (container.indexOfChild(childView) == -1) {
                container.addView(childView);
            } else {
                container.removeView(childView);
            }
        });
    }
}
