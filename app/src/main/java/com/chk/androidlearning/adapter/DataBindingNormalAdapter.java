package com.chk.androidlearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chk.androidlearning.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CHK on 20-12-2.
 */
public class DataBindingNormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<String> dataList;
    Context context;

    public DataBindingNormalAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data_binding_normal,parent,false);
        return new DataItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String data = dataList.get(position);

        if (holder instanceof DataItemViewHolder) {
            ((DataItemViewHolder) holder).dataItem.setText(data);
            ((DataItemViewHolder) holder).dataItem.setOnClickListener((view)-> Toast.makeText(context, "you click "+data, Toast.LENGTH_SHORT).show());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class DataItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dataItem)
        TextView dataItem;

        public DataItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(DataItemViewHolder.this,itemView);
        }
    }

}
