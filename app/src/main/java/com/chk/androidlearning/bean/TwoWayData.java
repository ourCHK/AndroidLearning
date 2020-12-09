package com.chk.androidlearning.bean;

import android.util.Log;

import com.chk.androidlearning.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by CHK on 20-12-8.
 */
public class TwoWayData extends BaseObservable {

    private final static String TAG = TwoWayData.class.getSimpleName();

    String data;

    boolean check;

    int age;

    @Bindable
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

    @Bindable
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        Log.i(TAG,"set check:"+check);
        if (check != this.check) {  //防止无限循环
            this.check = check;
            notifyPropertyChanged(BR.check);
        } else {
            Log.i(TAG,"check值未发生变化，无需更改UI");
        }
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.check);
    }
}
