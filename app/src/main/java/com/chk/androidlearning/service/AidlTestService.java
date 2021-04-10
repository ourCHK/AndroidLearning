package com.chk.androidlearning.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.chk.androidlearning.bean.aidl.Test;

public class AidlTestService extends Service {

    private final static String TAG = AidlTestService.class.getSimpleName();

    public AidlTestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.i(TAG,"aidl Test service bind");
        return new TestBinder();
    }

    class TestBinder extends Test.Stub {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }
    }
}