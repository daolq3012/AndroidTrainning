package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListSongFragment extends BaseFragment implements ListSongContract.Viewer {

    private ListSongPresenter mPresenter;
    private static final String TAG = "ListSongFragment";
    private RecyclerView mRecyclerView;
    private ListSongAdapter mListSongAdapter;

    public ListSongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_song, container, false);
        initViews(v);
        mPresenter.getDataExternal(getActivity());
        return v;
    }

    private void initViews(View v) {
        mPresenter = new ListSongPresenter();
        mPresenter.setView(this);
        mRecyclerView = v.findViewById(R.id.recycler_song);
        mListSongAdapter = new ListSongAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mListSongAdapter);
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
    public void onGetListTrackSuccess(List<Track> tracks) {
        mListSongAdapter.updateData(tracks);
    }
}
