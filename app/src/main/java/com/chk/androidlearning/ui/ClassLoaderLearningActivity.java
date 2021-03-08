package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.bean.BugTest;

public class ClassLoaderLearningActivity extends AppCompatActivity {

    public static final String TAG = ClassLoaderLearningActivity.class.getSimpleName();

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_loader_learning);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClassLoaderLearningActivity.this, BugTest.getString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}