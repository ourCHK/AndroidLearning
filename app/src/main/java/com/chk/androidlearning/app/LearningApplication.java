package com.chk.androidlearning.app;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Created by CHK on 20-12-27.
 */
@HiltAndroidApp
public class LearningApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
