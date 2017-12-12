package com.fstyle.androidtrainning.screen.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.fstyle.androidtrainning.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity
        implements MainContract.Viewer, OnTabSelectListener {

    private BottomBar mBottomBar;
    private MainPresenter mPresenter;
    private MainViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private SearchView mSearchView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        initViews();

        handleEvents();
    }

    private void handleEvents() {
        mPresenter.setViewer(this);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void initViews() {
        mBottomBar = findViewById(R.id.bottomBar);
        mViewPager = findViewById(R.id.viewPager);
        mBottomBar.setOnTabSelectListener(this);
        mPresenter = new MainPresenter();
        mPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tab_mysongs:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tab_online:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tab_another:
                mViewPager.setCurrentItem(2);
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
}
