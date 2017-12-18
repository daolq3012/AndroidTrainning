package com.fstyle.androidtrainning.screen.main.playingfragments.listsong;

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
public class ListSongFragment extends BaseFragment implements ListSongContract.Viewer {

    private ListSongPresenter mPresenter;

    public ListSongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_playing_list_song, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        mPresenter = new ListSongPresenter();
        mPresenter.setView(this);
    }
}
