package com.chk.androidlearning.pagingsource;

import android.util.Log;

import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.ui.PagingActivity;
import com.chk.androidlearning.util.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

/**
 * Created by CHK on 21-1-11.
 */
public class UserDataSource extends PageKeyedDataSource<Integer, User> {

    private static final String TAG = UserDataSource.class.getSimpleName()+" Paging";

    public UserDataSource() {
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, User> callback) {
        Log.i(TAG,"loadInitial "+ GsonUtil.toJson(params));
        List<User> userList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            userList.add(new User("CHK",i));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onResult(userList,0,1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {
        Log.i(TAG,"loadBefore "+ GsonUtil.toJson(params));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {
        Log.i(TAG,"loadAfter "+ GsonUtil.toJson(params));
//        int page = params.key;
        List<User> userList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            userList.add(new User("CHK",i));
        }
        callback.onResult(userList,params.key+1);
    }
}
