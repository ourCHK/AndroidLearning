package com.chk.androidlearning.util;

import android.widget.ImageView;

import com.chk.androidlearning.BuildConfig;
import com.squareup.picasso.Picasso;

/**
 * Created by CHK on 20-12-31.
 */
public class ImageLoadUtil {



    public static void loadImage(ImageView imageView,String url) {
        String serverUrl = BuildConfig.SERVER_ADDRESS+url;
        Picasso.get()
                .load(serverUrl)
                .into(imageView);
    }

}
