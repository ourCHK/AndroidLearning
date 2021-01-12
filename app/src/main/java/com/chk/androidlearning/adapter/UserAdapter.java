package com.chk.androidlearning.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ItemPagingBinding;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CHK on 21-1-12.
 */
public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserHolder> {

    public UserAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getName().equals(newItem.getName());
            }
        });
    }

    public UserAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    public UserAdapter(@NonNull AsyncDifferConfig<User> config) {
        super(config);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPagingBinding binding = ItemPagingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = getItem(position);
        holder.bindTo(user);
    }

    static class UserHolder extends RecyclerView.ViewHolder {

        ItemPagingBinding binding;

        public UserHolder(ItemPagingBinding binding) {
            super(binding.getRoot());
            UserHolder.this.binding = binding;
        }

        public void bindTo(User user) {
            binding.setUser(user);
        }
    }

}
