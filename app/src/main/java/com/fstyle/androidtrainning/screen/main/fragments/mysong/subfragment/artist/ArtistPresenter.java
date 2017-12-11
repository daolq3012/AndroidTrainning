package com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.artist;

/**
 * Created by Administrator on 12/10/17.
 */

public class ArtistPresenter implements ArtistContract.Presenter {

    private ArtistContract.Viewer mViewer;

    @Override
    public void setViewer(ArtistContract.Viewer viewer) {
        mViewer = viewer;
    }
}
