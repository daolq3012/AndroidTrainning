package com.fstyle.androidtrainning.screen.main.playingfragments.lyrics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsFragment extends Fragment implements LyricsContract.Viewer {

    private LyricsPresenter mPresenter;

    public LyricsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        mPresenter = new LyricsPresenter();
        mPresenter.setViewer(this);
    }
}
