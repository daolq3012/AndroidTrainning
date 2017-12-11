package com.fstyle.androidtrainning.screen.main.fragments.online;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.recent.RecentFragment;
import com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.topalbum
        .TopAlbumFragment;
import com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.toptrack
        .TopTrackFragment;

/**
 * Created by Administrator on 12/10/17.
 */

public class OnlineViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public OnlineViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RecentFragment();
        } else if (position == 1) {
            return new TopTrackFragment();
        } else if (position == 2) {
            return new TopAlbumFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.recent);
            case 1:
                return mContext.getString(R.string.top_track);
            case 2:
                return mContext.getString(R.string.top_album);
            default:
                return null;
        }
    }
}
