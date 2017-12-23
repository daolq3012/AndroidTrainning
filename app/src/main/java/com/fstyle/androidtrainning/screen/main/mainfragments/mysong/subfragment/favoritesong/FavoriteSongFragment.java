package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

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
import com.fstyle.androidtrainning.screen.BaseFragment;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteSongFragment extends BaseFragment
        implements FavoriteSongContract.Viewer, OnGetListTrack {

    private FavoriteSongPresenter mPresenter = new FavoriteSongPresenter();

    private RecyclerView mRecyclerView;
    private FavoriteRecyclerAdapter mFavoriteRecyclerAdapter;
    private OnItemFavoriteClickListener mOnItemFavoriteClickListener;

    public FavoriteSongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemFavoriteClickListener) {
            mOnItemFavoriteClickListener = (OnItemFavoriteClickListener) context;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorite_song, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        mRecyclerView = v.findViewById(R.id.recycler_song);
        mPresenter.setView(this);
        GetListAsyncTask getListAsyncTask = new GetListAsyncTask();
        getListAsyncTask.setOnGetListTrack(this);
        mFavoriteRecyclerAdapter =
                new FavoriteRecyclerAdapter(getActivity(), mOnItemFavoriteClickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mFavoriteRecyclerAdapter);

        getListAsyncTask.execute();
    }

    @Override
    public void onGetListSuccess(List<TrackEntity> tracks) {
        mFavoriteRecyclerAdapter.updateData(tracks);
    }
}
