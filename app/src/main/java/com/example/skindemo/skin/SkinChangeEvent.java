package com.example.skindemo.skin;

public class SkinChangeEvent {

    private String mSkinMode;

    public SkinChangeEvent(String skinMode) {
        mSkinMode = skinMode;
    }

    public String getSkinMode() {
        return mSkinMode;
    }
}
