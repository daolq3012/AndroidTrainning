package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.toptrack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.TopTracks;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopTrackFragment extends BaseFragment implements TopTrackContract.Viewer {

    private TopTrackPresenter mPresenter;
    private static final String TAG = "TopTrackFragment";

    public TopTrackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_track, container, false);
        initViews(v);

        mPresenter.getTopTrack();

        return v;
    }

    private void initViews(View v) {
        mPresenter = new TopTrackPresenter();
        mPresenter.setViewer(this);
        LastFmApi mApi = MainApplication.getLastFmApi();
        mPresenter.setApi(mApi);
    }

    @Override
    public void showToastFail(String message) {
        //TODO show fail message
    }

    @Override
    public void onGetTopTracksSuccess(TopTracks tracks) {
        //TODO do something with data albums
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

    @Override
    public void setPresenter(BasePresenter Presenter) {

    }
}
