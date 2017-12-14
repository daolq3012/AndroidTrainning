package com.fstyle.androidtrainning.screen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fstyle.androidtrainning.MainActivity;
import com.fstyle.androidtrainning.screen.tabfavorite.TabFavoriteFragment;
import com.fstyle.androidtrainning.screen.tabhome.TabHomeFragment;
import com.fstyle.androidtrainning.screen.tabprofile.TabProfileFragment;
import com.fstyle.androidtrainning.screen.tabsearch.TabSearchFragment;

/**
 * Created by huynh on 12/12/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final int TOTAL_TAB = 4;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case MainActivity.Tab.HOME:
                return new TabHomeFragment();
            case MainActivity.Tab.SEARCH:
                return new TabSearchFragment();
            case MainActivity.Tab.FAVORITE:
                return new TabFavoriteFragment();
            case MainActivity.Tab.PROFILE:
                return new TabProfileFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return TOTAL_TAB;
    }
}
