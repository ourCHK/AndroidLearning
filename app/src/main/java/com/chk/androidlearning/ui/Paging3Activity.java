package com.chk.androidlearning.ui;

import android.os.Bundle;
import android.util.Log;

import com.chk.androidlearning.R;
import com.chk.androidlearning.adapter.UserPaging3Adapter;
import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ActivityPaging3Binding;
import com.chk.androidlearning.pagingsource.UserPagingRxSource;
import com.chk.androidlearning.pagingsource.UserPagingSource;
import com.chk.androidlearning.ui.viewModel.Paging3ViewModel;
import com.chk.androidlearning.ui.viewModel.ViewModelAndLiveDataViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;
import androidx.paging.PagingSource;
import androidx.paging.rxjava3.PagingRx;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

public class Paging3Activity extends AppCompatActivity {

    private static final String TAG = Paging3Activity.class.getSimpleName()+" Paging";

    ActivityPaging3Binding binding;
    UserPaging3Adapter adapter;
    RecyclerView userRv;

    UserPagingSource source;
    Pager<Integer, User> pager;

    Flowable<PagingData<User>> pagingDataFlowable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_paging3);

//        dataInit();
//        viewInit();
        usingRxPaging();
    }

    void dataInit() {
        adapter = new UserPaging3Adapter();
        source = new UserPagingSource();
        pager = new Pager<>(new PagingConfig(10), new Function0<PagingSource<Integer, User>>() {
            @Override
            public PagingSource<Integer, User> invoke() {
                Log.i(TAG,"create source");
                return source;
            }
        });

        ViewModelAndLiveDataViewModel viewModel = new ViewModelProvider(this)
                .get(ViewModelAndLiveDataViewModel.class);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(viewModel);

        PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager),viewModelScope)
                .observe(this, new Observer<PagingData<User>>() {
                    @Override
                    public void onChanged(PagingData<User> userPagingData) {
                        Log.i(TAG,"change data!");
                        adapter.submitData(getLifecycle(),userPagingData);
                    }
                });

//        PagingLiveData.getLiveData(pager).observe(this, new Observer<PagingData<User>>() {
//            @Override
//            public void onChanged(PagingData<User> userPagingData) {
//                Log.i(TAG,"change data!");
//                adapter.submitData(getLifecycle(),userPagingData);
//            }
//        });
//        source.load(null, new Continuation<PagingSource.LoadResult<Integer, User>>() {
//            @NotNull
//            @Override
//            public CoroutineContext getContext() {
//                return null;
//            }
//
//            @Override
//            public void resumeWith(@NotNull Object o) {
//
//            }
//        })
    }

    void viewInit() {
        userRv = binding.pagingRv;
        userRv.setLayoutManager(new LinearLayoutManager(this));
        userRv.setAdapter(adapter);
    }

    void usingRxPaging() {
        adapter = new UserPaging3Adapter();
//        source = new UserPagingSource();
//        pager = new Pager<>(new PagingConfig(10), new Function0<PagingSource<Integer, User>>() {
//            @Override
//            public PagingSource<Integer, User> invoke() {
//                Log.i(TAG,"create source");
//                return source;
//            }
//        });


//        UserPagingRxSource source = new UserPagingRxSource();
//        Pager<Integer,User> pager = new Pager<>(new PagingConfig(20,20,false,20,100),()->source);
//        pagingDataFlowable = PagingRx.getFlowable(pager);
        Paging3ViewModel viewModel = new ViewModelProvider(this)
                .get(Paging3ViewModel.class);
        viewModel.pagingDataFlow.subscribe(userPagingData -> {
            adapter.submitData(getLifecycle(),userPagingData);
        });

        userRv = binding.pagingRv;
        userRv.setLayoutManager(new LinearLayoutManager(this));
        userRv.setAdapter(adapter);

//        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(viewModel);
//        PagingRx.cachedIn(pagingDataFlowable, coroutineScope);
//        pagingDataFlowable.subscribe(new Consumer<PagingData<User>>() {
//            @Override
//            public void accept(PagingData<User> userPagingData) throws Throwable {
//                Log.i(TAG,"go accept");
//                adapter.submitData(getLifecycle(),userPagingData);
//            }
//        });
    }

}