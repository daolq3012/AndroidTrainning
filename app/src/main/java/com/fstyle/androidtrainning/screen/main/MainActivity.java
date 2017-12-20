package com.fstyle.androidtrainning.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.screen.search.SearchActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BaseActivity
        implements MainContract.Viewer, OnTabSelectListener, SearchView.OnClickListener {

    private BottomBar mBottomBar;
    private MainPresenter mPresenter;
    private MainViewPagerAdapter mMainPagerAdapter;
    private PlayingViewPagerAdapter mPlayingPagerAdapter;
    private ViewPager mMainViewPager, mPlayingViewPager;
    private CircleIndicator mIndicator;
    private LastFmApi mApi;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        mBottomBar = findViewById(R.id.bottomBar);
        mMainViewPager = findViewById(R.id.viewPager);
        mPlayingViewPager = findViewById(R.id.pager_playing);
        mIndicator = findViewById(R.id.idicator);

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
