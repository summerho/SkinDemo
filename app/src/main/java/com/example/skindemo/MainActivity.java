package com.example.skindemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.skindemo.skin.SkinConfigManager;

public class MainActivity extends BaseActivity {

    private TextView mBtn1;
    private TextView mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn1 = findViewById(R.id.change_skin_btn);
        mBtn2 = findViewById(R.id.open_activity);
        mBtn1.setOnClickListener(view -> SkinConfigManager.getInstance().changeSkin());
        mBtn2.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
        Fragment firstFragment = new FirstFragment();
        addFragment(R.id.fragment_container, firstFragment, "abc");
    }
}