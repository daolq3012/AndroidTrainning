package com.fstyle.androidtrainning;

import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fstyle.androidtrainning.screen.ViewPagerAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class MainActivity extends AppCompatActivity implements OnTabSelectListener {
    private ViewPager mViewPager;
    private BottomBar mBottombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.unSwipViewPager);
        mBottombar = findViewById(R.id.bottomBar);
        mBottombar.setOnTabSelectListener(this);
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
    @IntDef({Tab.HOME, Tab.SEARCH, Tab.FAVORITE, Tab.PROFILE})
    public @interface Tab {
        int HOME = 0;
        int SEARCH = 1;
        int FAVORITE = 2;
        int PROFILE = 3;
    }
}
