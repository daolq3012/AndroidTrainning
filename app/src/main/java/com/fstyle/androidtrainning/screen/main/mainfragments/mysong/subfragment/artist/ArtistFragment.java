package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.artist;

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
public class ArtistFragment extends BaseFragment implements ArtistContract.Viewer {

    private ArtistPresenter mPresenter;

    public ArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        mPresenter = new ArtistPresenter();
        mPresenter.setView(this);
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
