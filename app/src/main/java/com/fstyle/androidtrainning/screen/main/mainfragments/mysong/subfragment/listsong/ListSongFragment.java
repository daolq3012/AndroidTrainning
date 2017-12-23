package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong
        .GetListAsyncTask;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong
        .OnGetListTrack;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListSongFragment extends BaseFragment
        implements ListSongContract.Viewer, OnFavoriteClick, OnTrackInsert, OnGetListTrack,
        OnTrackDelete {

    private ListSongPresenter mPresenter;
    private static final String TAG = "ListSongFragment";
    private RecyclerView mRecyclerView;
    private ListSongAdapter mListSongAdapter;
    private GetListAsyncTask mGetListAsyncTask;
    private List<Track> mTracks = new ArrayList<>();
    private OnItemListSongClickListener mOnItemListSongClickListener;

    public ListSongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemListSongClickListener) {
            mOnItemListSongClickListener = (OnItemListSongClickListener) context;
        }
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
        mListSongAdapter = new ListSongAdapter(getActivity().getApplicationContext(),
                mOnItemListSongClickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mListSongAdapter);
        mListSongAdapter.setOnFavoriteClick(this);

        mGetListAsyncTask = new GetListAsyncTask();
        mGetListAsyncTask.setOnGetListTrack(this);
        mGetListAsyncTask.execute();
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
        mTracks = tracks;
        mListSongAdapter.updateData(tracks);
    }

    @Override
    public void onFavoriteClicked(Track track) {
        TrackEntity trackEntity = new TrackEntity();
        trackEntity.setNameSong(track.getName());
        trackEntity.setNameArtist(track.getNameArtist());
        trackEntity.setPath(track.getTrackData());

        InsertAsyncTask mInsertAsyncTask = new InsertAsyncTask();
        mInsertAsyncTask.setOnTrackInsert(this);
        mInsertAsyncTask.execute(trackEntity);
    }

    @Override
    public void onUnFavoriteClicked(Track track) {
        TrackEntity trackEntity = new TrackEntity();
        trackEntity.setNameSong(track.getName());
        trackEntity.setNameArtist(track.getNameArtist());

        DeleteAsyncTask mDeleteAsyncTask = new DeleteAsyncTask();
        mDeleteAsyncTask.setOnTrackDelete(this);
        mDeleteAsyncTask.execute(trackEntity);
    }

    @Override
    public void onInsertSuccess() {

    }

    @Override
    public void onGetListSuccess(List<TrackEntity> tracks) {
        mListSongAdapter.updateFavorite(tracks);
    }

    @Override
    public void onDeleteSuccess() {

    }
}
