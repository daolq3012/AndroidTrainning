package com.fstyle.androidtrainning.screen.main.mainfragments.mysong;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album.AlbumFragment;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.artist
        .ArtistFragment;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong
        .FavoriteSongFragment;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong
        .ListSongFragment;

/**
 * Created by Administrator on 12/10/17.
 */

public class MySongViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private static final int TOTAL_TAB_MY_SONG = 4;

    public MySongViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Tab.LIST_SONG:
                return new ListSongFragment();
            case Tab.ALBUM:
                return new AlbumFragment();
            case Tab.ARTIST:
                return new ArtistFragment();
            case Tab.FAVORITE_SONG:
                return new FavoriteSongFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_TAB_MY_SONG;
    }

    public String getPageTitle(int position) {
        switch (position) {
            case Tab.LIST_SONG:
                return mContext.getString(R.string.list_song);
            case Tab.ALBUM:
                return mContext.getString(R.string.album);
            case Tab.ARTIST:
                return mContext.getString(R.string.artist);
            case Tab.FAVORITE_SONG:
                return mContext.getString(R.string.favorite_song);
            default:
                return null;
        }
    }

    /**
     * IntDef Tab
     */

    @IntDef({
            Tab.LIST_SONG, Tab.ALBUM, Tab.ARTIST, Tab.FAVORITE_SONG
    })
    public @interface Tab {
        int LIST_SONG = 0;
        int ALBUM = 1;
        int ARTIST = 2;
        int FAVORITE_SONG = 3;
    }
}
