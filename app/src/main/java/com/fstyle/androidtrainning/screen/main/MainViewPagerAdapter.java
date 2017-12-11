package com.fstyle.androidtrainning.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.screen.main.fragments.another.AnotherFragment;
import com.fstyle.androidtrainning.screen.main.fragments.mysong.MySongFragment;
import com.fstyle.androidtrainning.screen.main.fragments.online.OnlineFragment;

/**
 * Created by Administrator on 12/10/17.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MySongFragment();
        } else if (position == 1) {
            return new OnlineFragment();
        } else if (position == 2) {
            return new AnotherFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
