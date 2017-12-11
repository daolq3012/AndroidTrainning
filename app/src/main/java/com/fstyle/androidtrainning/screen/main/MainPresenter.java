package com.fstyle.androidtrainning.screen.main;

/**
 * Created by Administrator on 12/10/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.Viewer mViewer;

    public void setViewer(MainContract.Viewer viewer) {
        mViewer = viewer;
    }
}
