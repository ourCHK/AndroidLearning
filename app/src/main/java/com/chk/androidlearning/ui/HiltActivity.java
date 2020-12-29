package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.util.Log;

import com.chk.androidlearning.R;
import com.chk.androidlearning.di.DiTest;
import com.chk.androidlearning.di.DiTestContainer;
import com.chk.androidlearning.di.module.ModuleBean;

import javax.inject.Inject;

@AndroidEntryPoint
public class HiltActivity extends AppCompatActivity {

    private final static String TAG  = HiltActivity.class.getSimpleName();

    @Inject //基础用法
    DiTest diTest;

    @Inject
    DiTestContainer diTestContainer;    //带构造器的注入

    @Inject
    ModuleBean moduleBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);

        Log.i(TAG,"diTest:"+diTest);
        Log.i(TAG,"diTestContainer:"+diTestContainer.getDiTest());
        Log.i(TAG,"moduleBean:"+moduleBean);
    }
}