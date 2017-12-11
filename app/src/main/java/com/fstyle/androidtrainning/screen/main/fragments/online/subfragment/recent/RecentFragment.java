package com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.recent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment implements RecentContract.Viewer {

    private RecentPresenter mPresenter;

    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recent, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        mPresenter = new RecentPresenter();
        mPresenter.setViewer(this);
    }
}
