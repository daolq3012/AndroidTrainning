package com.fstyle.androidtrainning.screen.main;

import android.os.Bundle;
import android.support.annotation.IntDef;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.widget.UnSwipeViewPager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity
        implements MainContract.MainView, OnTabSelectListener {

    MainContract.Presenter mPresenter;
    private UnSwipeViewPager mViewPager;
    private BottomBar mBottombar;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter();
        mPresenter.setView(this);

        initViews();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(Page.LIMIT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.view_pager_main);
        mBottombar = findViewById(R.id.bottom_bar_main);
        mBottombar.setOnTabSelectListener(this);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tabhome:
                mViewPager.setCurrentItem(Tab.HOME);
                break;
            case R.id.tabsearch:
                mViewPager.setCurrentItem(Tab.SEARCH);
                break;
            case R.id.tabfavorite:
                mViewPager.setCurrentItem(Tab.FAVORITE);
                break;
            case R.id.tabprofile:
                mViewPager.setCurrentItem(Tab.PROFILE);
                break;
            default:
                break;
        }
    }

    /**
     * IntDef Tab.
     */
    @IntDef({ Tab.HOME, Tab.SEARCH, Tab.FAVORITE, Tab.PROFILE })
    public @interface Tab {
        int HOME = 0;
        int SEARCH = 1;
        int FAVORITE = 2;
        int PROFILE = 3;
    }

    /**
     * IntDef Page Limit.
     */
    @IntDef({Page.LIMIT})
    public @interface Page {
        int LIMIT = 3;
    }
}
