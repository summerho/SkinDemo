package com.example.skindemo;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skindemo.skin.SkinChangeEvent;
import com.example.skindemo.skin.SkinConfigManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public Resources getResources() {
        Resources skinResources = SkinConfigManager.getInstance().getSkinResource();
        if (skinResources != null) {
            return skinResources;
        } else {
            return super.getResources();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSkinChange(SkinChangeEvent event) {
        changeSkin();
    }

    public void changeSkin() {
        // 子类实现
    }
}
