package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.chk.androidlearning.R;
import com.chk.androidlearning.bean.TwoWayData;
import com.chk.androidlearning.databinding.ActivityTwoWayAndAdapterBindingBinding;

public class TwoWayAndAdapterBindingActivity extends AppCompatActivity {

    ActivityTwoWayAndAdapterBindingBinding binding;

    TwoWayData twoWayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_two_way_and_adapter_binding);
        init();
    }

    void init() {
        initData();
        initView();
    }

    void initData() {
        twoWayData = new TwoWayData();
        twoWayData.setData("是否勾选");
        twoWayData.setCheck(false);
        twoWayData.setAge(100);
        binding.setTwoWayData(twoWayData);

    }

    void initView() {
    }



}