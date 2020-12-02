package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.chk.androidlearning.R;

public class AniActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani);
        textView = findViewById(R.id.textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AniActivity.this, "you click textView!", Toast.LENGTH_SHORT).show();
            }
        });
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.ani_tran);
//        animation.setFillAfter(true);
//        textView.startAnimation(animation);

//        textView.post(new Runnable() {
//            @Override
//            public void run() {
//                textView.scrollBy(50,0);
//                textView.postInvalidate();
//
//            }
//        });

        ObjectAnimator.ofFloat(textView,"translationX",0,100).setDuration(1000).start();
    }
}
