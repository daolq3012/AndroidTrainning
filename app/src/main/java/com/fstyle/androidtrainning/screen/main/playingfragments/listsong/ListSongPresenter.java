package com.fstyle.androidtrainning.screen.main.playingfragments.listsong;

/**
 * Created by Administrator on 12/10/17.
 */

public class ListSongPresenter implements ListSongContract.Presenter {

    private ListSongContract.Viewer mViewer;

    @Override
    public void setViewer(ListSongContract.Viewer viewer) {
        mViewer = viewer;
    }
}
