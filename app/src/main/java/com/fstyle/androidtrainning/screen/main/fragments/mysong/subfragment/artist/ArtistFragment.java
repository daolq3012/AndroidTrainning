package com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.artist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment implements ArtistContract.Viewer {

    private ArtistPresenter mPresenter;

    public ArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        mPresenter = new ArtistPresenter();
        mPresenter.setViewer(this);
    }
}
