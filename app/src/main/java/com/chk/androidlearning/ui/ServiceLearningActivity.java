package com.chk.androidlearning.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import com.chk.androidlearning.R;
import com.chk.androidlearning.service.FirstService;
import com.chk.androidlearning.service.MessengerService;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceLearningActivity extends AppCompatActivity {

    private static final String TAG = ServiceLearningActivity.class.getSimpleName();

    Button startOtherActivity;
    Button startService;
    Button stopService;
    Button bindService;
    Button unbindService;
    Button invokeBindService;

    Button bindMessengerService;
    Button unbindMessengerService;
    Button invokeMessengerService;


    FirstServiceConnection mConnection;
    FirstService service;
    boolean isBound;

    Messenger mMsger;
    MessengerServiceConnection mMsgerConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_learning);

        mConnection = new FirstServiceConnection();

        startOtherActivity = findViewById(R.id.startOtherActivity);
        startOtherActivity.setOnClickListener((view)->{
            Intent intent = new Intent(this, Service2Activity.class);
            startActivity(intent);
        });

        startService = findViewById(R.id.startService);
        startService.setOnClickListener((view)->{
            Intent intent = new Intent(this, FirstService.class);
            startService(intent);
        });

        stopService = findViewById(R.id.stopService);
        stopService.setOnClickListener((view)->{
            Intent intent = new Intent(this, FirstService.class);
            stopService(intent);
        });

        bindService = findViewById(R.id.bindService);
        bindService.setOnClickListener((view)->{
            bindFirstService();
        });

        unbindService = findViewById(R.id.unbindService);
        unbindService.setOnClickListener((view)->{
            unBoundFirstService();
        });

        invokeBindService = findViewById(R.id.invokeBindService);
        invokeBindService.setOnClickListener((view)->{
            service.invokeService();
        });

        invokeMessengerService = findViewById(R.id.invoke_messenger_service);
        invokeMessengerService.setOnClickListener((view)->{
            if (mMsger != null) {
                try {
                    mMsger.send(Message.obtain(null,MessengerService.MSG_SAY_HELLO));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        mMsgerConnection = new MessengerServiceConnection();

        bindMessengerService = findViewById(R.id.bind_messenger_service);
        bindMessengerService.setOnClickListener((view)->{
            bindMessengerService();
        });

        unbindMessengerService = findViewById(R.id.unbind_messenger_service);
        unbindMessengerService.setOnClickListener((view)->{
            unbindMessengerService();
        });

    }

    void bindFirstService() {
        Intent intent = new Intent(this, FirstService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    void unBoundFirstService() {
        unbindService(mConnection);
    }

    void bindMessengerService() {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent,mMsgerConnection,BIND_AUTO_CREATE);
    }

    void unbindMessengerService() {
        unbindService(mMsgerConnection);
    }

    class FirstServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FirstService.ServiceBinder binder = (FirstService.ServiceBinder) service;
            ServiceLearningActivity.this.service = binder.getService();
            isBound = true;
            Log.i(TAG,"onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ServiceLearningActivity.this.service = null;
            Log.i(TAG,"onServiceDisconnected");
        }
    }

    class MessengerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMsger = new Messenger(service);    //直接利用iBinder创建service即可
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMsger = null;
        }
    }


}