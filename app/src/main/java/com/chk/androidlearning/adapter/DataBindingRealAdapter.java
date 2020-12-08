package com.chk.androidlearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.bean.DataBindUser;
import com.chk.androidlearning.databinding.ItemDataBindingRealBinding;
import com.chk.androidlearning.listener.UserBindingClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CHK on 20-12-7.
 */
public class DataBindingRealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataBindUser> dataList;
    Context context;

    public DataBindingRealAdapter(List<DataBindUser> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataBindingRealBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_data_binding_real,parent,false);
        return new DataBindRealViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataBindUser user = dataList.get(position);
        user.setIndex(position);
        if (holder instanceof DataBindRealViewHolder) {
            ((DataBindRealViewHolder) holder).binding.setUser(user);
            ((DataBindRealViewHolder) holder).binding.setClickListener((bindUser) -> Toast.makeText(context, "you click:"+bindUser.getName(), Toast.LENGTH_SHORT).show());
            ((DataBindRealViewHolder) holder).binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class DataBindRealViewHolder extends RecyclerView.ViewHolder {

        ItemDataBindingRealBinding binding;


        public DataBindRealViewHolder(ItemDataBindingRealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemDataBindingRealBinding getBinding() {
            return binding;
        }
    }
}
