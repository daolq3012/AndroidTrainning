package com.fstyle.androidtrainning.screen.main;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;

/**
 * Created by Administrator on 12/10/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.Viewer mViewer;
    private LastFmApi mApi;
    private static final String TAG = "MainPresenter";

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

    @Override
    public void setApi(LastFmApi api) {
        mApi = api;
    }
}
