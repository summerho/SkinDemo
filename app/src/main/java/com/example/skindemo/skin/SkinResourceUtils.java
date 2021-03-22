package com.example.skindemo.skin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 皮肤资源工具类
 */
public class SkinResourceUtils {

    /**
     * 获取皮肤资源
     *
     * @param skinApk app文件
     * @param metrics metrics
     * @param config  config
     * @return 皮肤资源对象
     */
    public static Resources getPluginResources(File skinApk, DisplayMetrics metrics,
                                               Configuration config) throws Exception {
        Resources resources = new Resources(getPluginAssetManager(skinApk), metrics, config);
        return resources;
    }

    /**
     * 获取AssetManager对象
     *
     * @param skinApk 皮肤apk文件
     * @return AssetManager对象
     * @throws Exception
     */
    public static AssetManager getPluginAssetManager(File skinApk) throws Exception {
        Class c = AssetManager.class;
        AssetManager assetManager = (AssetManager) c.newInstance();
        Method method = c.getDeclaredMethod("addAssetPath", String.class);
        method.invoke(assetManager, skinApk.getAbsolutePath());
        return assetManager;
    }

    /**
     * 获取apk包名称
     *
     * @param skinPkgPath
     * @param context
     * @return
     */
    public static String getPluginPackageName(String skinPkgPath, Context context) {
        if (context == null || TextUtils.isEmpty(skinPkgPath)) {
            return "";
        }
        PackageManager mPm = context.getPackageManager();
        PackageInfo mInfo = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
        return mInfo.packageName;
    }
}
