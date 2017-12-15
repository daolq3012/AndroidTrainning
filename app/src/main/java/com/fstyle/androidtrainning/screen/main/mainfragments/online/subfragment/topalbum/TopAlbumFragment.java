package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.topalbum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.screen.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopAlbumFragment extends BaseFragment implements TopAlbumContract.Viewer {

    private RecyclerView mRecyclerView;
    private TopAlbumAdapter mTopAlbumAdapter;
    private TopAlbumPresenter mPresenter;
    private static final String TAG = "TopAlbumFragment";
    private static final int TWO_COLUMN = 2;

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
        mRecyclerView = v.findViewById(R.id.recycler_top_album);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), TWO_COLUMN);
        mRecyclerView.setLayoutManager(manager);
        mTopAlbumAdapter = new TopAlbumAdapter(getActivity());
        mRecyclerView.setAdapter(mTopAlbumAdapter);
        mPresenter = new TopAlbumPresenter();
        LastFmApi mApi = MainApplication.getLastFmApi();
        mPresenter.setApi(mApi);
        mPresenter.setView(this);
    }

    @Override
    public void showToastFail(String message) {
        //TODO show fail message
    }

    @Override
    public void onListAlbumSuccess(List<Album> albums) {
        mTopAlbumAdapter.updateData(albums);
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
