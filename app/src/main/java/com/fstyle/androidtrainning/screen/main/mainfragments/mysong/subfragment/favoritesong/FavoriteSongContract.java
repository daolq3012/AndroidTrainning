package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

/**
 * Created by Administrator on 12/10/17.
 */

public interface FavoriteSongContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
