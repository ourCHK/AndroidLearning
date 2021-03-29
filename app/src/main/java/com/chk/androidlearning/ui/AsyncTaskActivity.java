package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.chk.androidlearning.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncTaskActivity extends AppCompatActivity {

    private static final String TAG = AsyncTaskActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        asyncTaskTest();
    }

    @SuppressLint("StaticFieldLeak")
    void asyncTaskTest() {
        AsyncTask<Integer, Integer, Boolean> test = new AsyncTask<Integer, Integer, Boolean>() {

            @Override
            protected void onPreExecute() { //主线程
                Log.i(TAG, "onPreExecute:" + Thread.currentThread());
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {//主线程
                Log.i(TAG, "onPostExecute:" + aBoolean + "  " + Thread.currentThread());
            }

            @Override
            protected void onProgressUpdate(Integer... values) {//主线程
                Log.i(TAG, "on progress:" + values[0] + " " + Thread.currentThread());
            }

            @Override
            protected Boolean doInBackground(Integer... integers) {//AsyncTask内部线程池

                Log.i(TAG, "doInBackground:" + integers[0] + "  " + Thread.currentThread());
                for (int i = integers[0]; i < 100; i++) {
                    publishProgress(i);
                }
                return true;
            }
        };
        test.execute(1);
    }

}