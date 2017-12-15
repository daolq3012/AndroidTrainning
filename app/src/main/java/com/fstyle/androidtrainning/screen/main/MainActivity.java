package com.fstyle.androidtrainning.screen.main;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BaseActivity implements MainContract.Viewer, OnTabSelectListener {

    private BottomBar mBottomBar;
    private MainPresenter mPresenter;
    private MainViewPagerAdapter mMainPagerAdapter;
    private PlayingViewPagerAdapter mPlayingPagerAdapter;
    private ViewPager mMainViewPager, mPlayingViewPager;
    private SearchView mSearchView;
    private CircleIndicator mIndicator;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        mBottomBar = findViewById(R.id.bottomBar);
        mMainViewPager = findViewById(R.id.viewPager);
        mPlayingViewPager = findViewById(R.id.pager_playing);
        mIndicator = findViewById(R.id.idicator);
        mBottomBar.setOnTabSelectListener(this);
        mPresenter = new MainPresenter();
        mMainPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mPlayingPagerAdapter = new PlayingViewPagerAdapter(getSupportFragmentManager());

        mPresenter.setViewer(this);
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
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem item = menu.findItem(R.id.search_view);
        mSearchView = (SearchView) item.getActionView();
        return true;
    }

    @IntDef({ Tab.MY_SONG, Tab.ONLINE, Tab.ANOTHER })
    public @interface Tab {
        int MY_SONG = 0;
        int ONLINE = 1;
        int ANOTHER = 2;
    }
}
