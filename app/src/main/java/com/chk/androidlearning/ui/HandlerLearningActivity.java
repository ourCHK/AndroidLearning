package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.chk.androidlearning.R;

import java.lang.ref.WeakReference;

public class HandlerLearningActivity extends AppCompatActivity {

    private final static String TAG = HandlerLearningActivity.class.getSimpleName();

    String name = "123";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_learning);

//        handler =  new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Toast.makeText(HandlerLearningActivity.this, "receiver message!"+msg.what+name, Toast.LENGTH_SHORT).show();
//            }
//        };
//        handler.sendEmptyMessageDelayed(0,2 * 1000 * 60);


        MyHandler myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(0,2 * 1000 * 60);

        LooperThread thread = new LooperThread();
        thread.start();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                thread.getHandler().sendEmptyMessage(0x0A);
            }
        },2000);

//        Handler handler1 = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                Log.i(TAG,"handler1:"+msg.what+" "+name);
//                return false;
//            }
//        });
//        handler1.sendEmptyMessageDelayed(0,2 * 1000 * 60);

//        Handler handler2 = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                Log.i(TAG,"handler2:"+msg.what);
//                return false;
//            }
//        });



//        handler1.sendEmptyMessage(1);
//        handler2.sendEmptyMessage(2);

    }

    static class LooperThread extends Thread {

        Handler handler;

        @Override
        public void run() {
            Log.i(TAG,"looperThread启动");
            Looper.prepare();
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.i(TAG,"receive message:"+msg.what);
                }
            };
            Looper.loop();
        }

        public Handler getHandler() {
            return handler;
        }

        public void setHandler(Handler handler) {
            this.handler = handler;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    static class MyHandler extends Handler {
        WeakReference<HandlerLearningActivity> handlerLearningActivityWR;
        public MyHandler(HandlerLearningActivity handlerLearningActivity) {
            handlerLearningActivityWR = new WeakReference<>(handlerLearningActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerLearningActivity handlerLearningActivity = handlerLearningActivityWR.get();
            if (handlerLearningActivity == null) {
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(handlerLearningActivity, "receive1"+handlerLearningActivity.name, Toast.LENGTH_SHORT).show();
                default:
                    Toast.makeText(handlerLearningActivity, "receive"+msg.what, Toast.LENGTH_SHORT).show();
            }
        }
    }

}