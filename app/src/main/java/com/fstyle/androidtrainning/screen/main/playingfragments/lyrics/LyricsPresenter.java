package com.fstyle.androidtrainning.screen.main.playingfragments.lyrics;

/**
 * Created by Administrator on 12/10/17.
 */

public class LyricsPresenter implements LyricsContract.Presenter {

    private LyricsContract.Viewer mViewer;

    @Override
    public void setView(LyricsContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
