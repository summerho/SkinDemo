package com.example.skindemo.skin;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

public class SkinConfigManager {

    /**
     * 夜间模式
     */
    private static final String SKIN_MODE_NIGHT = "night";
    /**
     * 日间模式
     */
    private static final String SKIN_MODE_LIGHT = "light";
    /**
     * 当前皮肤模式
     */
    private static final String KEY_CURRENT_SKIN_MODE = "current_skin_mode";

    private static final String NIGHT_SKIN_RESOURCE_NAME = "night_skin_resource.apk";

    private Context mContext;
    /**
     * 皮肤资源对象
     */
    private SkinResources mSkinResources;
    private SharedPreferences mSharedPreferences;

    private static class SingletonHolder {
        private static final SkinConfigManager instance = new SkinConfigManager();
    }

    private SkinConfigManager() {}

    public static SkinConfigManager getInstance() {
        return SingletonHolder.instance;
    }

    public void init(Context context) {
        mContext = context;
        mSkinResources = new SkinResources(context.getResources());
        mSharedPreferences = context.getSharedPreferences("SkinConfig", Context.MODE_PRIVATE);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        changeSkin();
    }

    public void changeSkin() {
        if (isCurrentNightMode()) {
            changeToNightMode();
        } else {
            changeToLightMode();
        }
    }

    public boolean isCurrentNightMode() {
        String currentSkinMode = SKIN_MODE_LIGHT;
        if (mSharedPreferences != null) {
            currentSkinMode = mSharedPreferences.getString(KEY_CURRENT_SKIN_MODE, SKIN_MODE_LIGHT);
        }
        return TextUtils.equals(currentSkinMode, SKIN_MODE_NIGHT);
    }

    private void changeToNightMode() {

    }

    private void changeToLightMode() {

    }

    private String getNightSkinPath() {
        String skinDirName = mContext.getFilesDir().getAbsolutePath() + File.separator + "skin";
        File file = new File(skinDirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return skinDirName + File.separator + NIGHT_SKIN_RESOURCE_NAME;
    }
}
