package com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.topalbum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopAlbumFragment extends Fragment implements TopAlbumContract.Viewer {

    private TopAlbumPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private TopAlbumRecyclerAdapter mTopAlbumRecyclerAdapter;
    private static final int SPAN_COUNT = 3;

    public TopAlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_album, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        mPresenter = new TopAlbumPresenter();
        mPresenter.setViewer(this);
        mRecyclerView = v.findViewById(R.id.recycler_top_album);
        //test
        List<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");
        test.add("6");
        test.add("7");
        test.add("8");
        test.add("9");
        mTopAlbumRecyclerAdapter = new TopAlbumRecyclerAdapter(test);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mTopAlbumRecyclerAdapter);
    }
}
