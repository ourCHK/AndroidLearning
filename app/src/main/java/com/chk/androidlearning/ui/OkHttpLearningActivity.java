package com.chk.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chk.androidlearning.R;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OkHttpLearningActivity extends AppCompatActivity {

    private final static String TAG = OkHttpLearningActivity.class.getSimpleName();

    Executor executor = Executors.newCachedThreadPool();

    Button button;

    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_learning);


//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                testOkHttp();
//            }
//        });
//
//        testOkHttpAsync();
        button = findViewById(R.id.get_cache);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testOKHttpCache();
            }
        });
//        testOKHttpCache();
    }

    OkHttpClient clientInstant() {
        if (client == null) {
            File CacheFile = getExternalCacheDir();
            Cache cache = new Cache(CacheFile,10 * 1024 * 1024);
            client = new OkHttpClient.Builder()
                    .addInterceptor(new CacheControlInterceptor())
                    .cache(cache)
                    .build();
        }
        return client;
    }

    void testOKHttpCache() {

//        CacheControl cacheControl = new CacheControl.Builder()
//                .maxAge(2, TimeUnit.MINUTES)
//                .build();
        OkHttpClient client = clientInstant();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/learning/getCache")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"get cache",e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG,"get Cache:"+response.body().string());
            }
        });
    }

    void testOkHttp() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/learning/test")
                .build();
        try (Response response = client.newCall(request).execute()) {
            Log.i(TAG,"responseString:"+response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void testOkHttpAsync() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/learning/test")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"Async responseString error",e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG,"Async responseString:"+response.body().string());
            }
        });
    }

    boolean netAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }

    class CacheControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!netAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OkHttpLearningActivity.this, "您现在使用的缓存数据！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return chain.proceed(request);
        }
    }


}