package com.example.skindemo.skin;

import android.content.res.Resources;

public class ResourceObj {

    private Resources mRes;

    private String mResPkgName;

    public ResourceObj(Resources res, String pkgName) {
        mRes = res;
        mResPkgName = pkgName;
    }

    public Resources getMRes() {
        return mRes;
    }

    public String getMResPkgName() {
        return mResPkgName;
    }

    public boolean isValid() {
        return mRes != null;
    }
}
