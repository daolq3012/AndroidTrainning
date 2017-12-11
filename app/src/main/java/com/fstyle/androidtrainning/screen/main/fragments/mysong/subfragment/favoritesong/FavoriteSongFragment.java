package com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.favoritesong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteSongFragment extends Fragment implements FavoriteSongContract.Viewer {

    private FavoriteSongPresenter mPresenter;

    public FavoriteSongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorite_song, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        mPresenter = new FavoriteSongPresenter();
        mPresenter.setViewer(this);
    }
}
