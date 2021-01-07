package com.chk.androidlearning.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.chk.androidlearning.R;
import com.chk.androidlearning.databinding.ItemBannerBindingBinding;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CustomBannerActivity extends AppCompatActivity {

    MZBannerView<String> mzBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        viewInit();
    }

    void viewInit() {
        List<String> dataList = new ArrayList<>();
//        for (int i=0; i<2; i++) {
        dataList.add("/adImage/72GVec.jpg");
        dataList.add("/adImage/DHL57t.jpg");
//        }

        mzBannerView = findViewById(R.id.banner);
        mzBannerView.setPages(dataList, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new MZViewHolder<String>() {
                    ItemBannerBindingBinding bannerBinding;
//                    private ImageView imageView;
                    @Override
                    public View createView(Context context) {
                        bannerBinding = ItemBannerBindingBinding.inflate(LayoutInflater.from(context));
                        return bannerBinding.getRoot();
//                        View view = LayoutInflater.from(context).inflate(R.layout.item_banner,null);
//                        imageView = (ImageView) view.findViewById(R.id.image);
//                        return view;
                    }

                    @Override
                    public void onBind(Context context, int i, String o) {
                        bannerBinding.setImageUrl(o);
//                        imageView.setImageDrawable(context.getDrawable(R.drawable.ic_launcher_background));
//                        ImageLoadUtil.loadImage(imageView,o);
                    }
                };
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        mzBannerView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
//        mzBannerView.start();
        super.onResume();
    }
}