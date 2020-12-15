package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.chk.androidlearning.R;
import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ActivityViewModelAndLiveDataBinding;
import com.chk.androidlearning.ui.viewModel.ViewModelAndLiveDataViewModel;

public class ViewModelAndLiveDataActivity extends AppCompatActivity {

    ActivityViewModelAndLiveDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewInitDataBinding();
//        viewInitNotDataBinding();
    }

    void viewInitNotDataBinding() {
        setContentView(R.layout.activity_view_model_and_live_data);
        ViewModelAndLiveDataViewModel viewModel = new ViewModelProvider(this)
                .get(ViewModelAndLiveDataViewModel.class);
        TextView userText = findViewById(R.id.userText);
        Button changeUser = findViewById(R.id.changeUser);

        viewModel.getUser().observe(this, (user)->{
            userText.setText(user.getName());
        });

        changeUser.setOnClickListener((view)->{
            User user = new User(String.valueOf(System.currentTimeMillis()),19);
            viewModel.getUser().setValue(user);
        });

    }

    void viewInitDataBinding() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_model_and_live_data);
        ViewModelAndLiveDataViewModel viewModel = new ViewModelProvider(this).get(ViewModelAndLiveDataViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.changeUser.setOnClickListener((view)->{
            viewModel.clickChangeUser();
        });
    }




}