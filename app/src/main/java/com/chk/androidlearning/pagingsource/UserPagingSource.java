package com.chk.androidlearning.pagingsource;

import android.util.Log;

import com.chk.androidlearning.bean.User;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import androidx.paging.ListenableFuturePagingSource;

/**
 * Created by CHK on 21-1-12.
 */
public class UserPagingSource extends ListenableFuturePagingSource<Integer, User> {

    private final static String TAG = UserPagingSource.class.getSimpleName()+ "Paging";

    Executor mBgExecutor;

    public UserPagingSource() {
        mBgExecutor = Executors.newSingleThreadExecutor();
    }

    @NotNull
    @Override
    public ListenableFuture<LoadResult<Integer, User>> loadFuture(@NotNull LoadParams<Integer> loadParams) {
        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;
        Log.i(TAG,"loadFuture");
        ListenableFuture<User> userFuture = new ListenableFuture<User>() {
            @Override
            public void addListener(Runnable listener, Executor executor) {

            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                Log.i(TAG,"is done");
                return true;
            }

            @Override
            public User get() throws ExecutionException, InterruptedException {
                Log.i(TAG,"get");
                return new User("CHK",18);
            }

            @Override
            public User get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                Log.i(TAG,"get from timeout");
                return new User("CHK",18);
            }
        };
        ListenableFuture<LoadResult<Integer, User>> pageFuture = Futures.transform(userFuture, new Function<User, LoadResult<Integer, User>>() {
            @NullableDecl
            @Override
            public LoadResult<Integer, User> apply(@NullableDecl User input) {
                Log.i(TAG,"load result!");
                List<User> userList = new ArrayList<>();
                for (int i=0; i<3; i++) {
                    userList.add(new User("CHK"+i,18));
                }
                return toLoadResult(userList,page);
            }
        }, mBgExecutor);
        ListenableFuture<LoadResult<Integer, User>> partialLoadResultFuture = Futures.catching(
                pageFuture, Exception.class,
                LoadResult.Error::new, mBgExecutor);

        return Futures.catching(partialLoadResultFuture,
                IOException.class, LoadResult.Error::new, mBgExecutor);
    }

    private LoadResult<Integer, User> toLoadResult(List<User> userList, int page) {
        return new LoadResult.Page(userList, page == 1 ? null : page - 1, page + 1);
    }

}
