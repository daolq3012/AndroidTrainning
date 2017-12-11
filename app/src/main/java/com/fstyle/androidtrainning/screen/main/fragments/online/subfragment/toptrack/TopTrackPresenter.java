package com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.toptrack;

/**
 * Created by Administrator on 12/10/17.
 */

public class TopTrackPresenter implements TopTrackContract.Presenter {

    private TopTrackContract.Viewer mViewer;

    @Override
    public void setViewer(TopTrackContract.Viewer viewer) {
        mViewer = viewer;
    }
}
