package com.chk.androidlearning.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.chk.androidlearning.R;

import androidx.appcompat.app.AppCompatActivity;

public class LeakCanaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0x01:
                        Toast.makeText(LeakCanaryActivity.this, "hh", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        handler.sendEmptyMessageDelayed(0x01,60000);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(LeakCanaryActivity.this, "memory leak", Toast.LENGTH_SHORT).show();
//            }
//        },60000);
    }
}