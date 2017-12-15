package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

/**
 * Created by Administrator on 12/10/17.
 */

public class FavoriteSongPresenter implements FavoriteSongContract.Presenter {

    private FavoriteSongContract.Viewer mViewer;

    @Override
    public void setViewer(FavoriteSongContract.Viewer viewer) {
        mViewer = viewer;
    }
}
