package com.boo.exercise.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boo.exercise.R;
import com.boo.exercise.adapter.RecyclerViewListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment {
    private List<String> dataList;

    public TwoFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置RecyclerView的缓存大小
        recyclerView.setItemViewCacheSize(10);
        RecyclerViewListAdapter adapter = new RecyclerViewListAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // 创建 ItemTouchHelper
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 初始化数据
     */
    private void initListData() {
        dataList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            dataList.add("Item " + i);
        }
    }

    /**
     * ItemTouchHelperCallback 用于处理拖拽和滑动删除的回调
     */
    public static class ItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {
        private final RecyclerViewListAdapter adapter;

        public ItemTouchHelperCallback(RecyclerViewListAdapter adapter) {
            // dragDirs支持上下拖拽，swipeDirs不支持滑动删除
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
            this.adapter = adapter;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            adapter.onItemMove(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            // 不需要处理滑动删除
        }

        // 可选实现：当拖拽状态改变时调用
        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG && viewHolder != null) {
                viewHolder.itemView.setBackgroundColor(Color.LTGRAY); // 拖拽时背景变灰色
            }
        }

        // 可选实现：当拖拽结束时调用
        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT); // 拖拽结束恢复背景
        }
    }
}