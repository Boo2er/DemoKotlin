package com.boo.exercise.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<String> dataList;

    public RecyclerViewAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 创建 ViewHolder：用来创建并初始化一个子项的视图。
        TextView textView = new TextView(parent.getContext());
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 绑定数据到 ViewHolder：将数据绑定到视图指定位置的 ViewHolder
        holder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        // 管理数据集大小：告诉 RecyclerView 有多少个子项
        return dataList.size();
    }

    /**
     * ViewHolder 是一种设计模式，通过减少频繁调用 findViewById() 的次数来优化视图查找操作，从而加快列表滚动时的响应速度。
     * RecyclerView 中，ViewHolder 被用来保存对列表项中各个视图组件的引用，以便于数据绑定和视图复用。
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
