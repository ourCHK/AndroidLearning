package com.chk.androidlearning.ui;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.databinding.ActivityExoPlayerBinding;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.DefaultDatabaseProvider;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheEvictor;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

public class ExoPlayerActivity extends AppCompatActivity {

    ActivityExoPlayerBinding binding;
    PlayerView playerView;

    ExoPlayer player;
    SimpleCache simpleCache;
    ImageView fullScreen;

    boolean isFull;

    int playerViewHeightDp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_exo_player);
        playerView = binding.playerView;
        playerViewHeightDp = (int) getResources().getDimension(R.dimen.exo_view_height);
        fullScreen = playerView.findViewById(R.id.full_screen);
        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFull) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    if(getSupportActionBar() != null){
                        getSupportActionBar().show();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = playerViewHeightDp;
//                    params.height = (int) ( playerViewHeightDp * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);
                    fullScreen.setImageDrawable(ContextCompat.getDrawable(ExoPlayerActivity.this,R.drawable.ic_full));
                    isFull = false;
                }else{
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    if(getSupportActionBar() != null){
                        getSupportActionBar().hide();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playerView.setLayoutParams(params);
                    fullScreen.setImageDrawable(ContextCompat.getDrawable(ExoPlayerActivity.this,R.drawable.ic_full_exit));
                    isFull = true;
                }
                Toast.makeText(ExoPlayerActivity.this, "full", Toast.LENGTH_SHORT).show();
            }
        });
        initPlayer();
    }

    void initPlayer() {
        player = new SimpleExoPlayer.Builder(this,new DefaultRenderersFactory(this),new DefaultExtractorsFactory())
                .setTrackSelector(new DefaultTrackSelector(this))
                .setLoadControl(new DefaultLoadControl())
                .build();
        playerView.setPlayer(player);
        player.setPlayWhenReady(false);
//        player.seekTo();
        initMediaResource(player);
    }

    void initMediaResource(ExoPlayer player) {
        String movieAddress = "http://10.0.2.2:8080/video/VY8ZgD.mp4";
        Uri uri = Uri.parse(movieAddress);
//        MediaSource source = buildProgressMediaSource(uri);
        MediaSource source = buildProgressMediaSourceCache(uri);
        player.setMediaSource(source,true);
        player.prepare();
    }

//    private MediaSource buildMediaSource(Uri uri) {
//        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab"))
//                .createMediaSource(uri);
//    }

    private MediaSource buildProgressMediaSource(Uri uri) {
        return new ProgressiveMediaSource.Factory(new DefaultHttpDataSourceFactory("ExoPlayerTest"))
                .createMediaSource(MediaItem.fromUri(uri));
    }

    private MediaSource buildProgressMediaSourceCache(Uri uri) {
        CacheDataSource.Factory factory = new CacheDataSource.Factory();
        long cacheSize = 100 * 1024 * 1024;     //100m
        CacheEvictor cacheEvictor = new LeastRecentlyUsedCacheEvictor(cacheSize);
        simpleCache = new SimpleCache(new File(this.getCacheDir(),"media"),cacheEvictor);
        factory.setCache(simpleCache);

//        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        DefaultDataSourceFactory defaultDatasourceFactory = new DefaultDataSourceFactory(this,
//                bandwidthMeter,
//                new DefaultHttpDataSourceFactory("ExoPlayer", bandwidthMeter));
        factory.setUpstreamDataSourceFactory(new DefaultHttpDataSourceFactory("ExoPLayer"));
        return new ProgressiveMediaSource.Factory(factory)
                .createMediaSource(MediaItem.fromUri(uri));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            if (simpleCache != null) {
                simpleCache.release();  //不要在UI线程做释放？？？
            }
        }
    }



}