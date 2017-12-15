package com.fstyle.androidtrainning.screen.main;

/**
 * Created by Administrator on 12/10/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.Viewer mViewer;

    @Override
    public void setView(MainContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
