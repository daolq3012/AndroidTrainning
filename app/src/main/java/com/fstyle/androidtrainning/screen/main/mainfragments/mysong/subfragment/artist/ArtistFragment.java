package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.artist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.songbelongartist.SongBelongArtistActivity;
import com.fstyle.androidtrainning.utils.Constant;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends BaseFragment
        implements ArtistContract.Viewer, OnItemClickListener {

    private ArtistPresenter mPresenter;
    private ArtistAdapter mArtistAdapter;
    private RecyclerView mRecyclerView;

    public ArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist, container, false);
        initViews(v);
        mPresenter.getDataExternal(getActivity());
        return v;
    }

    private void initViews(View v) {
        mPresenter = new ArtistPresenter();
        mPresenter.setView(this);
        mRecyclerView = v.findViewById(R.id.recycler_artist_offline);
        mArtistAdapter = new ArtistAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mArtistAdapter);
        mArtistAdapter.setOnItemClickListener(this);
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
    public void onGetListArtistSuccess(List<Artist> artists) {
        mArtistAdapter.updateData(artists);
    }

    @Override
    public void onItemClicked(String name) {
        startActivity(new Intent(getActivity(), SongBelongArtistActivity.class).putExtra(
                Constant.EXTRA_NAME_ARTIST, name));
    }
}
