package com.fstyle.androidtrainning.screen.main.playingfragments.listsong;

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
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.main.MainActivity;
import com.fstyle.androidtrainning.screen.main.OnListChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListSongFragment extends BaseFragment implements ListSongContract.Viewer,
        OnListChangeListener {

    private ListSongPresenter mPresenter;
    private OnItemSubListSongClickListener mOnItemSubListSongClickListener;
    private RecyclerView mRecyclerView;
    private ListSongRecyclerAdapter mListSongRecyclerAdapter;

    public ListSongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSubListSongClickListener) {
            mOnItemSubListSongClickListener = (OnItemSubListSongClickListener) context;
        }
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
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mListSongRecyclerAdapter =
                new ListSongRecyclerAdapter(getActivity().getApplicationContext(),
                        mOnItemSubListSongClickListener);
        LinearLayoutManager manager =
                new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mListSongRecyclerAdapter);
        updateAdapter();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setOnListChangeListener(this);
        }
    }

    private void updateAdapter() {
        if (getActivity() instanceof MainActivity) {
            mListSongRecyclerAdapter.updateData(((MainActivity) getActivity()).getTrackList());
        }
    }

    @Override
    public void onListChanged() {
        updateAdapter();
    }
}
