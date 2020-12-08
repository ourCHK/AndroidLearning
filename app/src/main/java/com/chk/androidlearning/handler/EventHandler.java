package com.chk.androidlearning.handler;

import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by CHK on 20-12-4.
 */
public class EventHandler {

    public void clickButton(View view) {
        Toast.makeText(view.getContext(), "you click the button!", Toast.LENGTH_SHORT).show();
    }

    public void addData(View view) {
        Toast.makeText(view.getContext(), "click add String", Toast.LENGTH_SHORT).show();
    }

}
