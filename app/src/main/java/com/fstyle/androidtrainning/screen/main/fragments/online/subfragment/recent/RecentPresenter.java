package com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.recent;

/**
 * Created by Administrator on 12/10/17.
 */

public class RecentPresenter implements RecentContract.Presenter {

    private RecentContract.Viewer mViewer;

    @Override
    public void setViewer(RecentContract.Viewer viewer) {
        mViewer = viewer;
    }
}
