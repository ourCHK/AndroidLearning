package com.chk.androidlearning.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class FirstService extends Service {

    private final static String TAG = FirstService.class.getSimpleName();

    int invokeTime;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int value = super.onStartCommand(intent, flags, startId);
        Log.i(TAG,"on start command:"+value);
        return value;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG,"onBind");
        return new ServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    public void invokeService() {
        Toast.makeText(this, "you invoke the service,invoke Time:"+(++invokeTime), Toast.LENGTH_SHORT).show();
    }

    public class ServiceBinder extends Binder {
        public FirstService getService() {
            return FirstService.this;
        }
    }
}