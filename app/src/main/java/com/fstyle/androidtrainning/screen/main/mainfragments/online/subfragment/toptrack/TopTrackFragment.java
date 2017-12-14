package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.toptrack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseFragment;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopTrackFragment extends BaseFragment implements TopTrackContract.Viewer {

    private TopTrackPresenter mPresenter;
    private static final String TAG = "TopTrackFragment";
    private RecyclerView mRecyclerView;
    private TopTrackRecyclerAdapter mRecyclerAdapter;

    public TopTrackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_track, container, false);
        initViews(v);

        mPresenter.getTopTrack();

        return v;
    }

    private void initViews(View v) {
        mPresenter = new TopTrackPresenter();
        LastFmApi mApi = MainApplication.getLastFmApi();
        mPresenter.setApi(mApi);
        mPresenter.setView(this);
        mRecyclerView = v.findViewById(R.id.recycler_top_track);
        mRecyclerAdapter = new TopTrackRecyclerAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void showToastFail(String message) {
        //TODO show fail message
    }

    @Override
    public void onListTrackSuccess(List<Track> tracks) {
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
}
