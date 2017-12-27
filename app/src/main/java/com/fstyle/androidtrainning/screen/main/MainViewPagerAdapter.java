package com.fstyle.androidtrainning.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.MySongFragment;
import com.fstyle.androidtrainning.screen.main.mainfragments.online.OnlineFragment;

/**
 * Created by Administrator on 12/10/17.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private static final int TOTAL_TAB_MAIN = 2;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MainActivity.Tab.MY_SONG:
                return new MySongFragment();
            case MainActivity.Tab.ONLINE:
                return new OnlineFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_TAB_MAIN;
    }
}
