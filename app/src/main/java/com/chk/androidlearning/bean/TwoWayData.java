package com.chk.androidlearning.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by CHK on 20-12-8.
 */
public class TwoWayData extends BaseObservable {

    String data;

    @Bindable
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
