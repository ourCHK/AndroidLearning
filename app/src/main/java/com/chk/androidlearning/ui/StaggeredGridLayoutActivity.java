package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.chk.androidlearning.R;
import com.chk.androidlearning.databinding.ActivityStraggeredGridLayoutBinding;

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    ActivityStraggeredGridLayoutBinding binding;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_straggered_grid_layout);
        viewInit();
    }

    void viewInit() {
        rv = binding.rv;
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }
}