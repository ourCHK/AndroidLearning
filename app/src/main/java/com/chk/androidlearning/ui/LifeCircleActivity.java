package com.chk.androidlearning.ui;

import android.os.Bundle;

import com.chk.androidlearning.R;
import com.chk.androidlearning.observer.CustomLifeCycleObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

public class LifeCircleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_circle);

        getLifecycle().addObserver(new CustomLifeCycleObserver());
        getLifecycle().addObserver(new CustomLifeCycleObserver());
        testLambda();
    }

    void testLambda() {
        new Thread(
                () -> System.out.println("Thread start")
        ).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("haha");
            }
        }).start();
    }
}
