package com.chk.androidlearning.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.bean.LearningClass;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CHK on 20-12-2.
 */
public class LearningClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<LearningClass> learningClassList;
    Context context;

    public LearningClassAdapter(List<LearningClass> learningClassList, Context context) {
        this.learningClassList = learningClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_learing_class,parent,false);
        return new LearningClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        LearningClass learningClass = learningClassList.get(position);

        if (holder instanceof LearningClassViewHolder) {
            ((LearningClassViewHolder) holder).button.setText(learningClass.getName());
            ((LearningClassViewHolder) holder).button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = null;
                        intent = new Intent(context,Class.forName(learningClass.getActivityName()));
                        context.startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "未找到该类!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return learningClassList.size();
    }

    static class LearningClassViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.openLearningClass)
        Button button;

        public LearningClassViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(LearningClassViewHolder.this,itemView);
        }
    }

}
