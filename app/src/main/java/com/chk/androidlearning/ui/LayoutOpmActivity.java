package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.chk.androidlearning.R;

public class LayoutOpmActivity extends AppCompatActivity {

    Button vButton;
    ViewStub vViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_opm);

        vButton = findViewById(R.id.load_view_stub);
        vViewStub = findViewById(R.id.view_stub);

        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = vViewStub.inflate();
            }
        });
    }
}