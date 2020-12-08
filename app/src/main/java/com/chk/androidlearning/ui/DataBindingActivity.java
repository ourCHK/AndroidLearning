package com.chk.androidlearning.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.adapter.DataBindingNormalAdapter;
import com.chk.androidlearning.adapter.DataBindingRealAdapter;
import com.chk.androidlearning.bean.DataBindUser;
import com.chk.androidlearning.bean.OneWayData;
import com.chk.androidlearning.bean.OneWayDataField;
import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ActivityDataBindingBinding;
import com.chk.androidlearning.handler.EventHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DataBindingActivity extends AppCompatActivity {

    List<String> dataList = new ArrayList<>();

    List<DataBindUser> bindUserList = new ArrayList<>();

    ActivityDataBindingBinding binding;

    RecyclerView dataRecyclerView;

    OneWayData oneWayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    void init() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding);
        dataInit();
        viewInit();
    }

    void dataInit() {

        User user = new User("CHK",26);
        oneWayData = new OneWayData();
        oneWayData.setData("This is the one Way Data");
        OneWayDataField oneWayDataField = new OneWayDataField(new ObservableField<>("这是改变之前的数据"));

        binding.setBindUser(user);
        binding.setOneWayData(oneWayData);
        binding.setOneWayDataField(oneWayDataField);
        List<String> nameList = new ArrayList<>();
        nameList.add("Hello1");
        nameList.add("Hello2");
        binding.setNameList(nameList);
        binding.setButtonClick((view)-> Toast.makeText(this, "you click this", Toast.LENGTH_SHORT).show());
        binding.setOneWayDataChange((view)->{
            changeOneWayData(oneWayData);
        });
        binding.setOneWayDataField(oneWayDataField);
        binding.setOneWayDataFieldListener((view)->changeOneWayDataField(oneWayDataField));
        binding.setEventHandler(new EventHandler());

        dataList.add("1");
        dataList.add("2");
        dataList.add("3");
        dataList.add("4");
        dataList.add("5");
        initRealDataBindingData();
    }

    void viewInit() {
        dataRecyclerView = binding.dataRecyclerView;
        initRealRecyclerView();
    }

    void initNormalRecyclerView() {
        dataRecyclerView.setAdapter(new DataBindingNormalAdapter(dataList,this));
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void initRealDataBindingData() {
        for (int i=0; i<10; i++) {
            DataBindUser dataBindUser = new DataBindUser(String.valueOf(i),i);
            bindUserList.add(dataBindUser);
        }
    }

    void initRealRecyclerView() {
        dataRecyclerView.setAdapter(new DataBindingRealAdapter(bindUserList,this));
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void changeOneWayData(OneWayData oneWayData) {
        oneWayData.setData("This is the data after changed");
    }

    void changeOneWayDataField(OneWayDataField oneWayDataField) {
        oneWayDataField.name.set("这是改变之后的数据");
    }

}
