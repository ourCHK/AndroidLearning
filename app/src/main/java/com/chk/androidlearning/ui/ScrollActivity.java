package com.chk.androidlearning.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.adapter.ReAdapter;
import com.chk.androidlearning.custom.view.TouchEventLearningView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollActivity extends AppCompatActivity {

    private final String TAG = ScrollActivity.class.getSimpleName();


    @BindView(R.id.firstText)
    TextView firstText;

    @BindView(R.id.secondText)
    TextView secondText;

    @BindView(R.id.HelloWorld)
    TextView helloWorld;

    @BindView(R.id.HelloWorld2)
    TextView helloWorld2;

    @BindView(R.id.HelloWorld3)
    TextView helloWorld3;

    @BindView(R.id.fullTextView)
    TextView fullTextView;

    @BindView(R.id.TouchEventLearningView)
    TouchEventLearningView touchView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);
        viewInit();
    }

    void dataInit() {

    }

    void viewInit() {
//        helloWorld.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i(TAG,"helloWorld touch");
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        Toast.makeText(ScrollActivity.this, "click this", Toast.LENGTH_SHORT).show();
//                        return true;
//                }
//                return false;
//            }
//        });

        recyclerView.setAdapter(new ReAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        helloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollActivity.this, "click helloWorld", Toast.LENGTH_SHORT).show();
            }
        });

        helloWorld2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollActivity.this, "click helloWorld2", Toast.LENGTH_SHORT).show();
            }
        });

        helloWorld3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollActivity.this, "click helloWorld3", Toast.LENGTH_SHORT).show();
            }
        });

        fullTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollActivity.this, "click full textView!", Toast.LENGTH_SHORT).show();
            }
        });

        touchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollActivity.this, "you click this!", Toast.LENGTH_SHORT).show();
            }
        });
//        touchView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("ScrollActivity","onTOuch:"+event.getAction());
//                return true;
//            }
//        });
    }

}
