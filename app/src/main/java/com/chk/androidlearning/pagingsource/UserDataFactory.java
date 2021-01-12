package com.chk.androidlearning.pagingsource;

import com.chk.androidlearning.bean.User;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

/**
 * Created by CHK on 21-1-11.
 */
public class UserDataFactory extends PageKeyedDataSource.Factory<Integer, User> {

    private MutableLiveData<UserDataSource> sourceLiveDate = new MutableLiveData<>();
    private UserDataSource latestDataSource;


    @NonNull
    @Override
    public DataSource<Integer, User> create() {
        latestDataSource = new UserDataSource();
        sourceLiveDate.postValue(latestDataSource);
        return latestDataSource;
    }
}
