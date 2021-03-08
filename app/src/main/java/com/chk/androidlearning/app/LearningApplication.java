package com.chk.androidlearning.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.chk.androidlearning.util.FixUtil;

import java.io.File;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Created by CHK on 20-12-27.
 */
@HiltAndroidApp
public class LearningApplication extends Application {

    private  final static String TAG = LearningApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        String patchPath = getFilesDir()+ File.separator+"patch.dex";
        Log.i(TAG,"patchPath:"+patchPath);
        FixUtil.installPatch(base,patchPath);
    }
}
