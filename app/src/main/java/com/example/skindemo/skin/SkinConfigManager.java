package com.example.skindemo.skin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.example.skindemo.ThreadUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SkinConfigManager {

    private static final String TAG = "SSSkinConfigManager";
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

    private static final String ASSETS_PRE_SET_NIGHT_NAME = "skin/" + NIGHT_SKIN_RESOURCE_NAME;

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
        setSkin();
    }

    public Resources getSkinResource() {
        return mSkinResources;
    }

    private void setSkin() {
        if (isCurrentNightMode()) {
            changeToNightMode();
        } else {
            changeToLightMode();
        }
    }

    public void changeSkin() {
        if (isCurrentNightMode()) {
            changeToLightMode();
        } else {
            changeToNightMode();
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
        String skinPath = getNightSkinPath();
        loadSkin(skinPath, SKIN_MODE_NIGHT);
    }

    private void changeToLightMode() {
        // 重置皮肤资源
        mSkinResources.clear();
        mLoadSkinCallback.onLoadSkin(SKIN_MODE_LIGHT, true);
    }

    private String getNightSkinPath() {
        String skinDirName = mContext.getFilesDir().getAbsolutePath() + File.separator + "skin";
        File file = new File(skinDirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String skinPath = skinDirName + File.separator + NIGHT_SKIN_RESOURCE_NAME;
        copyFromAssetsToSdcard(true, ASSETS_PRE_SET_NIGHT_NAME, skinPath);
        return skinPath;
    }

    private final LoadSkinCallback mLoadSkinCallback = (changeSkinTo, isSuccess) -> {
        if (isSuccess) {
            saveCurrentSkinMode(changeSkinTo);
            notifySkinChange(changeSkinTo);
        }
    };

    /**
     * 加载皮肤资源
     * @param skinPath
     * @param changeSkinTo
     */
    private void loadSkin(String skinPath, String changeSkinTo) {
        ThreadUtils.dispatchToSubThread(() -> {
            do {
                File file = new File(skinPath);
                if (!file.exists()) {
                    break;
                }
                try {
                    Resources res = SkinResourceUtils.getPluginResources(file, mSkinResources.getDisplayMetrics(), mSkinResources.getConfiguration());
                    String pkgName = SkinResourceUtils.getPluginPackageName(skinPath, mContext);
                    updateSkin(res, pkgName);
                } catch (Exception e) {
                    Log.e(TAG, "loadSkin exception happened");
                    e.printStackTrace();
                    break;
                }
                mLoadSkinCallback.onLoadSkin(changeSkinTo, true);
                return;
            } while (false);
            mLoadSkinCallback.onLoadSkin(changeSkinTo,false);
        });
    }

    /**
     * 更新皮肤资源
     * @param resources
     * @param packageName
     */
    private void updateSkin(Resources resources, String packageName) {
        mSkinResources.clear();
        mSkinResources.push(new ResourceObj(resources, packageName));
    }

    /**
     * 保存皮肤模式
     * @param skinMode
     */
    private void saveCurrentSkinMode(String skinMode) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_CURRENT_SKIN_MODE, skinMode);
        editor.apply();
    }

    /**
     * 通知ui重新加载皮肤资源
     * @param changeSkinTo
     */
    private void notifySkinChange(String changeSkinTo) {
        SkinChangeEvent event = new SkinChangeEvent(changeSkinTo);
        EventBus.getDefault().post(event);
    }

    /**
     * 将assets下的文件拷贝到sd卡下
     *
     * @param isCover 是否覆盖已存在的目标文件
     * @param source
     * @param dest
     */
    public void copyFromAssetsToSdcard(boolean isCover, String source, String dest) {
        File file = new File(dest);
        if (isCover || !file.exists()) {
            File parent = new File(file.getParent());
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = mContext.getResources().getAssets().open(source);
                fos = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];
                int size;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
