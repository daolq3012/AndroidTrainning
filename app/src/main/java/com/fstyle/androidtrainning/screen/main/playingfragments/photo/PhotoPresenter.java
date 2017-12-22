package com.fstyle.androidtrainning.screen.main.playingfragments.photo;

/**
 * Created by Administrator on 12/10/17.
 */

public class PhotoPresenter implements PhotoContract.Presenter {

    private PhotoContract.Viewer mViewer;

    @Override
    public void setView(PhotoContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
