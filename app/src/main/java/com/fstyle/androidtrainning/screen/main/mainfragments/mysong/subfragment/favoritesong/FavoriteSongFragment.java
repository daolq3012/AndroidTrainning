package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteSongFragment extends BaseFragment implements FavoriteSongContract.Viewer {

    private FavoriteSongPresenter mPresenter;

    public FavoriteSongFragment() {
        // Required empty public constructor
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
        mPresenter = new FavoriteSongPresenter();
        mPresenter.setView(this);
    }
}
