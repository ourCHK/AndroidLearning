package com.chk.androidlearning.adapter;

import android.util.Log;
import android.widget.ImageView;


import com.chk.androidlearning.util.ImageLoadUtil;

import androidx.databinding.BindingAdapter;

/**
 * Created by CHK on 20-12-31.
 */
public class DataBindingAdapter {


    @BindingAdapter("imageUrl")
    public static void bindImageUrl(ImageView imageView,String imageUrl) {
        Log.i("DataBindingAdapter","bindImageUrl");
        ImageLoadUtil.loadImage(imageView,imageUrl);
    }

}
