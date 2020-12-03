package com.chk.androidlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.util.Log;

import com.chk.androidlearning.adapter.LearningClassAdapter;
import com.chk.androidlearning.bean.LearningClass;
import com.chk.androidlearning.util.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.learningRecyclerView)
    RecyclerView learningRecyclerView;

    List<LearningClass> learningClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initLearningClass();
        initView();
    }

    void initLearningClass() {
        try {
            InputStream inputStream = getResources().getAssets().open("LearningList.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            learningClassList = GsonUtil.fromJson(reader,new TypeToken<List<LearningClass>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void initView() {
        learningRecyclerView.setAdapter(new LearningClassAdapter(learningClassList,this));
        learningRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
