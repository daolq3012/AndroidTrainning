package com.fstyle.androidtrainning.screen.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong
        .ListSongAdapter;
import com.fstyle.androidtrainning.screen.search.SearchActivity;
import com.fstyle.androidtrainning.utils.Constant;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BaseActivity
        implements MainContract.Viewer, OnTabSelectListener,
        View.OnClickListener{

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
    private ImageView mTmageBack;
    private static final String TAG = "MainActivity";

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

                Log.d(TAG, "onReceive: " + track.getName());
            }
        };
        IntentFilter filter = new IntentFilter(Constant.ACTION_ITEM_PLAY);
        registerReceiver(mReceiver, filter);
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
        mTmageBack = findViewById(R.id.image_back);

        mApi = MainApplication.getLastFmApi();
        mBottomBar.setOnTabSelectListener(this);

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
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }

    @IntDef({ Tab.MY_SONG, Tab.ONLINE, Tab.ANOTHER })
    public @interface Tab {
        int MY_SONG = 0;
        int ONLINE = 1;
        int ANOTHER = 2;
    }
}
