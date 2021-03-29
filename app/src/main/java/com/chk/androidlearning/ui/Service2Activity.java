package com.chk.androidlearning.ui;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.chk.androidlearning.R;
import com.chk.androidlearning.service.FirstService;

import androidx.appcompat.app.AppCompatActivity;

public class Service2Activity extends AppCompatActivity {

    private static final String TAG = Service2Activity.class.getSimpleName();

    Button startOtherActivity;
    Button startService;
    Button stopService;
    Button bindService;
    Button unbindService;
    Button invokeBindService;

    Service2Activity.FirstServiceConnection mConnection;
    FirstService service;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_learning);

        mConnection = new Service2Activity.FirstServiceConnection();

        startOtherActivity = findViewById(R.id.startOtherActivity);
        startOtherActivity.setOnClickListener((view)->{
            Intent intent = new Intent(this, Service2Activity.class);
            startService(intent);
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
    }

    void bindFirstService() {
        Intent intent = new Intent(this, FirstService.class);
        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
    }

    void unBoundFirstService() {
        unbindService(mConnection);
    }

    class FirstServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FirstService.ServiceBinder binder = (FirstService.ServiceBinder) service;
            Service2Activity.this.service = binder.getService();
            isBound = true;
            Log.i(TAG,"onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Service2Activity.this.service = null;
            Log.i(TAG,"onServiceDisconnected");
        }
    }


}