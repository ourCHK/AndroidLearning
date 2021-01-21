package com.chk.androidlearning.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chk.androidlearning.bean.RecyclerItem;
import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ItemLoadingBinding;
import com.chk.androidlearning.databinding.ItemStaggerBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CHK on 21-1-14.
 */
public class StaggerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<RecyclerItem<User>> itemList;

    public StaggerAdapter(List<RecyclerItem<User>> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case RecyclerItem.DATA:
                return new DataViewHolder(ItemStaggerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
            case RecyclerItem.FOOTER:
                return new LoadingViewHolder(ItemLoadingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof LoadingViewHolder) {
//            ViewGroup.LayoutParams lp = ((LoadingViewHolder) holder).loadingContainer.getLayoutParams();
//            lp
//        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) layoutManager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    RecyclerItem<User> item = itemList.get(position);
                    if (item.getType() == RecyclerItem.FOOTER) {

                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        RecyclerItem<User> item = itemList.get(position);
        return item.getType();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        public DataViewHolder(ItemStaggerBinding binding) {
            super(binding.getRoot());
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {

        LinearLayout loadingContainer;

        public LoadingViewHolder(ItemLoadingBinding binding) {
            super(binding.getRoot());
            loadingContainer = binding.loadingContainer;
        }
    }

}
