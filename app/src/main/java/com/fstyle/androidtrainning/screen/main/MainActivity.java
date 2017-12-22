package com.fstyle.androidtrainning.screen.main;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.screen.search.SearchActivity;
import com.fstyle.androidtrainning.service.ServicePlayMusic;
import com.fstyle.androidtrainning.utils.Constant;
import com.fstyle.androidtrainning.utils.Utilities;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BaseActivity
        implements MainContract.Viewer, OnTabSelectListener, View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    private BottomBar mBottomBar;
    private MainPresenter mPresenter;
    private MainViewPagerAdapter mMainPagerAdapter;
    private PlayingViewPagerAdapter mPlayingPagerAdapter;
    private ViewPager mMainViewPager, mPlayingViewPager;
    private CircleIndicator mIndicator;
    private RelativeLayout mLayoutMain;
    private LinearLayout mLayoutPlaying, mLayoutMiniPlaying;
    private LastFmApi mApi;
    private BroadcastReceiver mReceiver;
    private Toolbar mToolbar;
    private ImageView mImageBack, mImagePause, mImagePrevious, mImageNext;
    private SeekBar mSeekBarTime;
    private TextView mTxtTextStart, mTxtTextTotal;
    private ServicePlayMusic mServicePlayMusic;
    private static final String TAG = "MainActivity";
    private boolean isConnect;
    private boolean isMusicPlaying = false;
    private Handler mHandler = new Handler();
    private Utilities mUtilities = new Utilities();

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnect = true;
            //get music service
            ServicePlayMusic.LocalBinder binder = (ServicePlayMusic.LocalBinder) service;
            mServicePlayMusic = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //receive
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mLayoutMain.setVisibility(View.GONE);
                mLayoutMiniPlaying.setVisibility(View.VISIBLE);
                mLayoutPlaying.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.GONE);
                Track track = intent.getParcelableExtra(Constant.EXTRA_TRACK_ITEM);
                connectService(track);
            }
        };
        IntentFilter filter = new IntentFilter(Constant.ACTION_ITEM_PLAY);
        registerReceiver(mReceiver, filter);
        initListeners();
    }

    private void connectService(Track track) {
        Intent intent = new Intent(this, ServicePlayMusic.class);
        intent.putExtra(Constant.EXTRA_TRACK_ITEM, track);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void initListeners() {
        mSeekBarTime.setOnSeekBarChangeListener(this);
        mImageNext.setOnClickListener(this);
        mImagePrevious.setOnClickListener(this);
        mImagePause.setOnClickListener(this);
        mImageBack.setOnClickListener(this);
        mBottomBar.setOnTabSelectListener(this);
        mLayoutMiniPlaying.setOnClickListener(this);
    }

    private void initViews() {
        mToolbar = findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        mBottomBar = findViewById(R.id.bottomBar);
        mMainViewPager = findViewById(R.id.viewPager);
        mLayoutMain = findViewById(R.id.layout_main);
        mLayoutPlaying = findViewById(R.id.layout_playing);
        mLayoutMiniPlaying = findViewById(R.id.layout_mini_playing);
        mPlayingViewPager = findViewById(R.id.pager_playing);
        mIndicator = findViewById(R.id.idicator);
        mImageBack = findViewById(R.id.image_back);
        mImagePause = findViewById(R.id.image_pause);
        mSeekBarTime = findViewById(R.id.seekBar);
        mTxtTextStart = findViewById(R.id.text_start);
        mTxtTextTotal = findViewById(R.id.text_total);
        mImageNext = findViewById(R.id.image_next);
        mImagePrevious = findViewById(R.id.image_previous);

        mApi = MainApplication.getLastFmApi();

        mPresenter = new MainPresenter();
        mPresenter.setApi(mApi);
        mPresenter.setView(this);

        mMainPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mPlayingPagerAdapter = new PlayingViewPagerAdapter(getSupportFragmentManager());
        mMainViewPager.setAdapter(mMainPagerAdapter);
        mPlayingViewPager.setAdapter(mPlayingPagerAdapter);
        mIndicator.setViewPager(mPlayingViewPager);
        mPlayingPagerAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());
    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tab_mysongs:
                mMainViewPager.setCurrentItem(Tab.MY_SONG);
                break;
            case R.id.tab_online:
                mMainViewPager.setCurrentItem(Tab.ONLINE);
                break;
            case R.id.tab_another:
                mMainViewPager.setCurrentItem(Tab.ANOTHER);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        unregisterReceiver(mReceiver);
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                mLayoutPlaying.setVisibility(View.GONE);
                mLayoutMain.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_mini_playing:
                mLayoutMain.setVisibility(View.GONE);
                mLayoutPlaying.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.GONE);
                break;
            case R.id.image_pause:
                if (!isMusicPlaying) {
                    changePauseImage();
                    mServicePlayMusic.resumeMusic();
                } else {
                    changePlayImage();
                    mServicePlayMusic.pauseMusic();
                }
                break;
            case R.id.image_previous:
                changePauseImage();
                mServicePlayMusic.previousTrack();
                break;
            case R.id.image_next:
                changePauseImage();
                mServicePlayMusic.nextTrack();
                break;
            default:
                break;
        }
    }

    private void changePauseImage() {
        isMusicPlaying = !isMusicPlaying;
        mImagePause.setImageResource(R.drawable.ic_pause);
    }

    private void changePlayImage() {
        isMusicPlaying = !isMusicPlaying;
        mImagePause.setImageResource(R.drawable.ic_play);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mServicePlayMusic.setMediaLength(progress);
            mServicePlayMusic.seekMusic();
        } else {
            mHandler.removeCallbacks(setupUI);
            mHandler.postDelayed(setupUI, 1000);
        }
    }

    private Runnable setupUI = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mServicePlayMusic.getMediaPlayer().getCurrentPosition();
            int totalPosition = mServicePlayMusic.getMediaPlayer().getDuration();
            String currentTime = mUtilities.milliSecondsToTimer(currentPosition);
            String totalTime = mUtilities.milliSecondsToTimer(totalPosition);
            mTxtTextStart.setText(currentTime);
            mTxtTextTotal.setText(totalTime);
            mSeekBarTime.setProgress(currentPosition);
            if (currentTime.equals(totalTime)) {
                mSeekBarTime.setProgress(0);
                mServicePlayMusic.nextTrack();
                mSeekBarTime.setMax(mServicePlayMusic.getMediaPlayer().getDuration());
                mTxtTextTotal.setText(mUtilities.milliSecondsToTimer(
                        mServicePlayMusic.getMediaPlayer().getDuration()));
                changePauseImage();
            }
            mHandler.postDelayed(setupUI, 1000);
        }
    };

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @IntDef({ Tab.MY_SONG, Tab.ONLINE, Tab.ANOTHER })
    public @interface Tab {
        int MY_SONG = 0;
        int ONLINE = 1;
        int ANOTHER = 2;
    }
}
