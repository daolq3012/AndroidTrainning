package com.fstyle.androidtrainning.screen.main;

import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fstyle.androidtrainning.screen.main.playingfragments.listsong.ListSongFragment;
import com.fstyle.androidtrainning.screen.main.playingfragments.lyrics.LyricsFragment;
import com.fstyle.androidtrainning.screen.main.playingfragments.photo.PhotoFragment;

/**
 * Created by Administrator on 12/11/17.
 */

public class PlayingViewPagerAdapter extends FragmentPagerAdapter {

    private static final int TOTAL_TAB_PLAYING = 3;

    public PlayingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Tab.PHOTO:
                return new PhotoFragment();
            case Tab.LYRICS:
                return new LyricsFragment();
            case Tab.LIST_PLAYING:
                return new ListSongFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_TAB_PLAYING;
    }

    /**
     * IntDef Tab
     */

    @IntDef({
            Tab.PHOTO, Tab.LYRICS, Tab.LIST_PLAYING
    })
    public @interface Tab {
        int PHOTO = 0;
        int LYRICS = 1;
        int LIST_PLAYING = 2;
    }
}
