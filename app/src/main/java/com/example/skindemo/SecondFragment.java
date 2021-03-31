package com.example.skindemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skindemo.skin.SkinConfigManager;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends BaseFragment {

    private RecyclerView mRv;
    private RvAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_layout, container, false);
        initRv(view);
        return view;
    }

    private void initRv(View rootView) {
        mRv = rootView.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRv.setHasFixedSize(true);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL);
        divider.setDrawable(SkinConfigManager.getInstance().getSkinResource().getDrawable(R.drawable.rv_divider));
        mRv.addItemDecoration(divider);
        mAdapter = new RvAdapter();
        mRv.setAdapter(mAdapter);
        rootView.postDelayed(() -> {
            List<Integer> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                data.add(i * 2);
            }
            mAdapter.setData(data);
        }, 1000);
    }
}
