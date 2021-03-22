package com.example.skindemo.skin;

import android.content.res.Resources;

import org.greenrobot.eventbus.EventBus;

public class SkinConfigManager {

    private SkinResources mSkinResources;

    private static class SingletonHolder {
        private static final SkinConfigManager instance = new SkinConfigManager();
    }

    private SkinConfigManager() {}

    public static SkinConfigManager getInstance() {
        return SingletonHolder.instance;
    }

    public void init(Resources resources) {
        mSkinResources = new SkinResources(resources);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
}
