package com.chk.androidlearning.ui.viewModel;

import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.pagingsource.UserPagingRxSource;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

/**
 * Created by CHK on 21-1-12.
 */
public class Paging3ViewModel extends ViewModel {

    public Flowable<PagingData<User>> pagingDataFlow;


    public Paging3ViewModel() {
        init();
    }

    void init() {
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
