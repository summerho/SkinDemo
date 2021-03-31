package com.example.skindemo;

import android.os.Bundle;
import android.view.View;
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
        TextView tv = findViewById(R.id.tv1);
        tv.setOnClickListener(view -> SkinConfigManager.getInstance().changeSkin());
    }
}
