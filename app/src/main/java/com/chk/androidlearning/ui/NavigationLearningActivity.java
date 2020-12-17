package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.chk.androidlearning.R;
import com.chk.androidlearning.ui.viewModel.FragmentSharedViewModel;

public class NavigationLearningActivity extends AppCompatActivity {

    FragmentSharedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_learning);
        dataInit();
    }

    void dataInit() {
        viewModel = new ViewModelProvider(this)
                .get(FragmentSharedViewModel.class);
    }
}