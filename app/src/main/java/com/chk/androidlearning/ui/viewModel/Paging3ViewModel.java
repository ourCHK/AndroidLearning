package com.chk.androidlearning.ui.viewModel;

import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.pagingsource.UserPagingRxSource;
import com.chk.androidlearning.pagingsource.UserPagingSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;
import androidx.paging.rxjava3.PagingRx;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

/**
 * Created by CHK on 21-1-12.
 */
public class Paging3ViewModel extends ViewModel {

    public Flowable<PagingData<User>> pagingDataFlow;

    public LiveData<PagingData<User>> pagingDataLiveData;

    public Paging3ViewModel() {
        initLive();
        initRx();
    }

    void initLive() {
        UserPagingSource source = new UserPagingSource();
        Pager<Integer,User> pager = new Pager<>(new PagingConfig(20,
                20,
                false,
                20,
                60),()->source);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        pagingDataLiveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager),coroutineScope);
    }

    void initRx() {
        UserPagingRxSource source = new UserPagingRxSource();
        Pager<Integer,User> pager = new Pager<>(new PagingConfig(20,
                20,
                false,
                20,
                60),()->source);
        pagingDataFlow = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(pagingDataFlow, coroutineScope);
    }

}
