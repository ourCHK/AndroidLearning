package com.chk.androidlearning.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

/**
 * Created by CHK on 20-12-8.
 */
public class OneWayDataField extends BaseObservable {

    public final ObservableField<String> name;

    public OneWayDataField(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<String> getName() {
        return name;
    }
}
