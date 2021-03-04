package com.chk.androidlearning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ItemUserBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CHK on 21-2-20.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    List<User> userList;

    public ItemAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bindUser(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;

        public ItemViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            ItemViewHolder.this.binding = binding;
        }

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bindUser(User user) {
            binding.setUser(user);
        }

    }

}
