package com.example.skindemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<Integer> list;

    public void setData(List<Integer> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder == null || position >= list.size()) {
            return;
        }
        if (holder instanceof ItemViewHolder) {
            int data = list.get(position);
            ((ItemViewHolder) holder).setData(data);
        }
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }

        public void setData(int data) {
            mTv.setText(data + "");
        }
    }
}
