package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.artist;

/**
 * Created by Administrator on 12/10/17.
 */

public class ArtistPresenter implements ArtistContract.Presenter {

    private ArtistContract.Viewer mViewer;

    @Override
    public void setView(ArtistContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
