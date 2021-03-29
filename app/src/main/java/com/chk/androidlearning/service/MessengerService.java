package com.chk.androidlearning.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MessengerService extends Service {

    private final static String TAG = MessengerService.class.getSimpleName();

    public final static int MSG_SAY_HELLO = 0x01;

    Messenger mMessenger;

    @SuppressLint("HandlerLeak")
    public MessengerService() {
        mMessenger = new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_SAY_HELLO:
                        Toast.makeText(MessengerService.this, "say hello!", Toast.LENGTH_SHORT).show();
                        Messenger client = msg.replyTo;
                        Bundle bundle = new Bundle();
                        bundle.putString("reply","this is the server,we have accept your message");
                        Message replyMsg = Message.obtain(null,MSG_SAY_HELLO);
                        replyMsg.setData(bundle);
                        try {
                            client.send(replyMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG,"onBind");
        return mMessenger.getBinder();
    }


}