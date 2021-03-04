package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chk.androidlearning.R;
import com.chk.androidlearning.adapter.ItemAdapter;
import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ActivityRecyclerViewLearningBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewLearningActivity extends AppCompatActivity {

    ActivityRecyclerViewLearningBinding binding;
    List<User> userList;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view_learning);

        viewInit();
    }

    void viewInit() {
        rv = binding.rv;

        userList = new ArrayList<>();
        for (int i=0; i<20; i++) {
            User user = new User("CHK"+i,i);
            userList.add(user);
        }

        ItemAdapter itemAdapter = new ItemAdapter(userList);
        rv.setAdapter(itemAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));


    }
}