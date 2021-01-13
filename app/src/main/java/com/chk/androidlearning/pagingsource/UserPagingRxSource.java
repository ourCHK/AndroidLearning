package com.chk.androidlearning.pagingsource;

import android.util.Log;

import com.chk.androidlearning.bean.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.paging.rxjava3.RxPagingSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by CHK on 21-1-12.
 */
public class UserPagingRxSource extends RxPagingSource<Integer, User> {


    private final static String TAG = UserPagingRxSource.class.getSimpleName();

    Executor mBgExecutor;

    public UserPagingRxSource() {
        mBgExecutor = Executors.newSingleThreadExecutor();
    }

    @NotNull
    @Override
    public Single<LoadResult<Integer, User>> loadSingle(@NotNull LoadParams<Integer> loadParams) {
        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;
        return Single.just(1)
//                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, LoadResult<Integer,User>>() {
                    @Override
                    public LoadResult<Integer, User> apply(Integer integer) throws Throwable {
                        Log.i(TAG,"LoadResult");
                        List<User> userList = new ArrayList<>();
                        for (int i=0; i<3; i++) {
                            userList.add(new User("CHK"+i,18));
                        }
                        return toLoadResult(userList,page);
                    }
                })
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, User> toLoadResult(List<User> userList, int page) {
        return new LoadResult.Page(userList, page == 1 ? null : page - 1, page + 1);
    }

    private LoadResult<Integer, User> toLoadResult(LoadParams<Integer> loadParams,@NonNull int response) {
        Log.i(TAG,"toLoadResult");
        List<User> userList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            userList.add(new User("CHK"+i,18));
        }

        return new LoadResult.Page<>(
                userList,
                null, // Only paging forward.
                loadParams.getKey()+1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }



}
