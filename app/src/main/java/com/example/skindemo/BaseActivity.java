package com.example.skindemo;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.Fragment;

import com.example.skindemo.skin.SkinChangeEvent;
import com.example.skindemo.skin.SkinConfigManager;
import com.example.skindemo.skin.SkinFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends AppCompatActivity {

    private SkinFactory mSkinFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinFactory = new SkinFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(), mSkinFactory);
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
        mSkinFactory.apply();
    }

    public void addFragment(int container, Fragment fragment, String tag) {
        if (null != fragment && !fragment.isAdded())
            getSupportFragmentManager().beginTransaction().add(container, fragment,
                    tag).commitAllowingStateLoss();
    }
}
