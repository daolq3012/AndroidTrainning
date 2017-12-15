package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.recent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.RecentTracks;
import com.fstyle.androidtrainning.screen.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment implements RecentContract.Viewer {

    private RecentPresenter mPresenter;
    private static final String TAG = "RecentFragment";
    private RecyclerView mRecyclerView;
    private RecentRecyclerAdapter mRecyclerAdapter;

    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recent, container, false);
        initViews(v);

        mPresenter.getRecentTrack();
        return v;
    }

    private void initViews(View v) {
        mPresenter = new RecentPresenter();
        mRecyclerView = v.findViewById(R.id.recycler_recent);
        mPresenter.setViewer(this);
        LastFmApi mApi = MainApplication.getLastFmApi();
        mPresenter.setApi(mApi);
        mRecyclerAdapter = new RecentRecyclerAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void showToastFail(String message) {
        //TODO show fail message
    }

    @Override
    public void onGetRecentTracksSuccess(RecentTracks tracks) {
        mRecyclerAdapter.updateData(tracks);
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
