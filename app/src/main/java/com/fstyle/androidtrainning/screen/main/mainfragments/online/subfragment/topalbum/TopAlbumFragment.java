package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.topalbum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.TopAlbums;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopAlbumFragment extends BaseFragment implements TopAlbumContract.Viewer {

    private TopAlbumPresenter mPresenter;
    private static final String TAG = "TopAlbumFragment";

    public TopAlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_album, container, false);
        initViews(v);

        mPresenter.getTopAlbum();

        return v;
    }

    private void initViews(View v) {
        mPresenter = new TopAlbumPresenter();
        mPresenter.setViewer(this);
        LastFmApi mApi = MainApplication.getLastFmApi();
        mPresenter.setApi(mApi);
    }

    @Override
    public void showToastFail(String message) {
        //TODO show fail message
    }

    @Override
    public void onGetTopAlbumsSuccess(TopAlbums albums) {
        //TODO do something with data albums
    }

    @Override
    public void setPresenter(BasePresenter Presenter) {

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }
}
