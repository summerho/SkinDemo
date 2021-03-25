package com.example.skindemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.skindemo.skin.SkinConfigManager;

public class SecondFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_layout, container, false);
        TextView btn = view.findViewById(R.id.tv2);
        btn.setOnClickListener(view1 -> SkinConfigManager.getInstance().changeSkin());
        return view;
    }
}
