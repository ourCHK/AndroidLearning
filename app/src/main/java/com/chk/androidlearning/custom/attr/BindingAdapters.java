package com.chk.androidlearning.custom.attr;

import android.util.Log;
import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * Created by CHK on 20-12-8.
 */
public class BindingAdapters {

    private final static String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter("testLeft")
    public static void setTestLeft(View view,int paddingLeft) {
        Log.i(TAG,"go set PaddingLeft!paddingLeft:"+paddingLeft);
        view.setPadding(paddingLeft,
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    @BindingAdapter("testRight")
    public static void setTestRight(View view,int paddingRight) {
        Log.i(TAG,"go set PaddingRight!paddingRight:"+paddingRight);
        view.setPadding(view.getPaddingLeft(),
                view.getPaddingTop(),
                paddingRight,
                view.getPaddingBottom());
    }

}
