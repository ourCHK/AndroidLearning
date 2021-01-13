package com.chk.androidlearning.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.adapter.UserAdapter;
import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.databinding.ActivityPagingBinding;
import com.chk.androidlearning.pagingsource.UserDataFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PagingActivity extends AppCompatActivity {

    private static final String TAG = PagingActivity.class.getSimpleName()+" Paging";

    ActivityPagingBinding binding;

    LiveData<PagedList<User>> userList;
    private DataSource<Integer,User> dataSource;
    UserDataFactory factory;
    RecyclerView pagingRv;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_paging);
        dataInit();
        viewInit();
    }

    void dataInit() {
        factory = new UserDataFactory();
        dataSource = factory.create();
        genDataSource();

        userAdapter = new UserAdapter();
    }

    void genDataSource() {
        userList = new LivePagedListBuilder<>(factory,10)
                .build();
    }

    void viewInit() {
        pagingRv = binding.pagingRv;
        pagingRv.setLayoutManager(new LinearLayoutManager(this));
        binding.loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invalidUserSource();
                Toast.makeText(PagingActivity.this, "click!", Toast.LENGTH_SHORT).show();
            }
        });
        pagingRv.setAdapter(userAdapter);
        userList.observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> userPagedList) {
                Log.i(TAG,"post user!");
                userAdapter.submitList(userPagedList);
            }
        });
    }

    void invalidUserSource() {
        Log.i(TAG,"invalidate");
        dataSource.invalidate();
    }
}