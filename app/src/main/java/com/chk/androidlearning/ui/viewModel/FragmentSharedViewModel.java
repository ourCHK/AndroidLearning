package com.chk.androidlearning.ui.viewModel;

import com.chk.androidlearning.bean.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by CHK on 20-12-15.
 */
public class FragmentSharedViewModel extends ViewModel {

    MutableLiveData<User> user = new MutableLiveData<>(null);   //注意这里的必须要有一个初始值null，否则初始化的订阅是不会收到通知的

    public MutableLiveData<User> getUser() {
        return user;
    }

    public boolean genUser() {
        user.postValue(new User("CHK",18));
        return true;
    }
}
