package com.fstyle.androidtrainning.screen.search;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.searchoffline.SearchOfflineFragment;
import com.fstyle.androidtrainning.screen.searchonline.SearchOnlineFragment;

/**
 * Created by Administrator on 12/19/17.
 */

public class SearchPagerAdapter extends FragmentPagerAdapter {

    private static final int TOTAL_TAB = 2;
    private Context mContext;

    public SearchPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Tab.ONLINE:
                return new SearchOnlineFragment();
            case Tab.OFFLINE:
                return new SearchOfflineFragment();
            default:
                return null;
        }
    }

    public String getPageTitle(int position) {
        switch (position) {
            case Tab.ONLINE:
                return mContext.getString(R.string.online);
            case Tab.OFFLINE:
                return mContext.getString(R.string.offline);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_TAB;
    }

    @IntDef({ Tab.ONLINE, Tab.OFFLINE })
    public @interface Tab {
        int ONLINE = 0;
        int OFFLINE = 1;
    }
}
