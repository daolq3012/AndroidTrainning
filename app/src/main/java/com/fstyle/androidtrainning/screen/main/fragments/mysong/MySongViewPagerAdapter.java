package com.fstyle.androidtrainning.screen.main.fragments.mysong;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.album.AlbumFragment;
import com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.artist.ArtistFragment;
import com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.favoritesong
        .FavoriteSongFragment;
import com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.listsong
        .ListSongFragment;

/**
 * Created by Administrator on 12/10/17.
 */

public class MySongViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MySongViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ListSongFragment();
        } else if (position == 1) {
            return new AlbumFragment();
        } else if (position == 2) {
            return new ArtistFragment();
        } else if (position == 3) {
            return new FavoriteSongFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.list_song);
            case 1:
                return mContext.getString(R.string.album);
            case 2:
                return mContext.getString(R.string.artist);
            case 3:
                return mContext.getString(R.string.favorite_song);
            default:
                return null;
        }
    }
}
