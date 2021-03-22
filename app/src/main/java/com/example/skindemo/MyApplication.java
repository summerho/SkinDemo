package com.example.skindemo;

import android.app.Application;

import com.example.skindemo.skin.SkinConfigManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinConfigManager.getInstance().init(this);
    }
}
