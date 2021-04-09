package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.chk.androidlearning.R;

public class EventLearningActivity extends AppCompatActivity {

    private static final String TAG = EventLearningActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_learning);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,"dispatchTouchEvent");
        Thread.dumpStack();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onUserLeaveHint() {
        Log.i(TAG,"onUserLeaveHint");
        super.onUserLeaveHint();
    }

    @Override
    public void onUserInteraction() {
        Log.i(TAG,"onUserInteraction");
        super.onUserInteraction();
    }

}