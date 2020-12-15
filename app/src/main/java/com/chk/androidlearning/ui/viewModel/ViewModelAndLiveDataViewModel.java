package com.chk.androidlearning.ui.viewModel;

import android.app.Application;
import android.widget.Toast;

import com.chk.androidlearning.bean.User;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by CHK on 20-12-14.
 */
public class ViewModelAndLiveDataViewModel extends AndroidViewModel {

    private MutableLiveData<User> user = new MutableLiveData<>();

    public ViewModelAndLiveDataViewModel(Application application) {
        super(application);
        User actUser = new User("CHK",18);
        user.postValue(actUser);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    /**
     * 点击改变用户
     */
    public void clickChangeUser() {
        Toast.makeText(getApplication(), "you click change user!", Toast.LENGTH_SHORT).show();
        user.setValue(new User(String.valueOf(System.currentTimeMillis()),19));
    }
}
