package com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.topalbum;

/**
 * Created by Administrator on 12/10/17.
 */

public class TopAlbumPresenter implements TopAlbumContract.Presenter {

    private TopAlbumContract.Viewer mViewer;

    @Override
    public void setViewer(TopAlbumContract.Viewer viewer) {
        mViewer = viewer;
    }
}
