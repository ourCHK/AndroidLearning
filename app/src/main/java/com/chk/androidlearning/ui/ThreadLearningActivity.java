package com.chk.androidlearning.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.chk.androidlearning.R;

import java.sql.Time;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLearningActivity extends AppCompatActivity {

    private static final String TAG = ThreadLearningActivity.class.getSimpleName();

    volatile int iLocal = 0;

    static ThreadLocal<Integer> iThread = new ThreadLocal<>();

    public static int getIThread() {
        return iThread.get();
    }

    static AtomicInteger ai = new AtomicInteger(0);

    static ThreadLocal<Integer> threadID = new ThreadLocal<Integer>() {
        @Nullable
        @Override
        protected Integer initialValue() {
            return ai.incrementAndGet();
        }
    };

    public static int getThreadID() {
        return threadID.get();
    }

    ThreadPoolExecutor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_learning);

        iThread.set(23333);
        executor = new ThreadPoolExecutor(4,8,10
                , TimeUnit.SECONDS,new LinkedBlockingDeque<>(10));

        executor.execute(new Runnable() {
            @Override
            public void run() {
                iThread.set(111);
                Log.i(TAG,"threadID1:"+getIThread());
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"threadID1:"+getIThread());
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                iThread.set(222);
                Log.i(TAG,"threadID2:"+getIThread());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"threadID:"+getIThread());
//                for (int i=0; i<100; i++) {
//                    Log.i(TAG+Thread.currentThread(),"iLocal:"+iLocal++);
////                    Log.i(TAG+Thread.currentThread(),"iThread:"+iThread.get());
//                }
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"handler:"+getIThread());
            }
        },10000);

//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG,"threadID:"+getThreadID());
//
////                for (int i=0; i<100; i++) {
////                    Log.i(TAG+Thread.currentThread(),"iLocal:"+iLocal++);
//////                    Log.i(TAG+Thread.currentThread(),"iThread:"+iThread.get());
////                }
//            }
//        });
    }

    
}