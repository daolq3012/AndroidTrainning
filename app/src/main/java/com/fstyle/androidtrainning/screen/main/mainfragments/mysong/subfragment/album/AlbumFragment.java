package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.screen.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends BaseFragment implements AlbumContract.Viewer {

    private RecyclerView mRecyclerView;
    private AlbumAdapter mAlbumAdapter;
    private AlbumPresenter mPresenter;
    private static final int COLUMN = 2;

    public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_album, container, false);
        initViews(v);
        mPresenter.getDataExternal(getActivity());
        return v;
    }

    private void initViews(View v) {
        mPresenter = new AlbumPresenter();
        mPresenter.setView(this);
        mRecyclerView = v.findViewById(R.id.recycler_album);
        mAlbumAdapter = new AlbumAdapter(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), COLUMN);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAlbumAdapter);
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
    public void onGetListAlbumSuccess(List<Album> albums) {
        mAlbumAdapter.updateData(albums);
    }
}
