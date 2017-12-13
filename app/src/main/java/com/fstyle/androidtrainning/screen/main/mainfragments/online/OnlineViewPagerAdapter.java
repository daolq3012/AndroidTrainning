package com.fstyle.androidtrainning.screen.main.mainfragments.online;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.recent
        .RecentFragment;
import com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.topalbum
        .TopAlbumFragment;
import com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.toptrack
        .TopTrackFragment;

/**
 * Created by Administrator on 12/10/17.
 */

public class OnlineViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private static final int TOTAL_TAB_ONLINE = 3;

    public OnlineViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Tab.RECENT:
                return new RecentFragment();
            case Tab.TOP_TRACK:
                return new TopTrackFragment();
            case Tab.TOP_ALBUM:
                return new TopAlbumFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_TAB_ONLINE;
    }

    public String getPageTitle(int position) {
        switch (position) {
            case Tab.RECENT:
                return mContext.getString(R.string.recent);
            case Tab.TOP_TRACK:
                return mContext.getString(R.string.top_track);
            case Tab.TOP_ALBUM:
                return mContext.getString(R.string.top_album);
            default:
                return null;
        }
    }

    /**
     * IntDef Tab
     */

    @IntDef({
            Tab.RECENT, Tab.TOP_TRACK, Tab.TOP_ALBUM
    })
    public @interface Tab {
        int RECENT = 0;
        int TOP_TRACK = 1;
        int TOP_ALBUM = 2;
    }
}
