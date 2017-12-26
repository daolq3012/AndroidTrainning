package com.fstyle.androidtrainning.screen.searchoffline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.utils.Constant;
import java.util.ArrayList;
import java.util.List;

/**
 * Searchoffline Screen.
 */
public class SearchOfflineFragment extends BaseFragment
        implements SearchOfflineContract.Viewer, OnItemSongClickListener, OnItemAlbumClickListener,
        OnItemArtistClickListener {

    SearchOfflineContract.Presenter mPresenter;
    private RecyclerView mRecyclerSong, mRecyclerAlbum, mRecyclerArtist;
    private SearchSongRecyclerAdapter mSearchSongRecyclerAdapter;
    private SearchAlbumRecyclerAdapter mSearchAlbumRecyclerAdapter;
    private SearchArtistRecyclerAdapter mSearchArtistRecyclerAdapter;
    private EditText mEditText;
    private ImageView mImageViewCancel;
    private TextView mTxtLabelSong, mTxtLabelAlbum, mTxtLabelArtist, mTxtNoData;
    private static final int SEARCH_AMOUNT = 4;

    public static SearchOfflineFragment newInstance() {
        return new SearchOfflineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SearchOfflinePresenter();
        mPresenter.setView(this);
        mPresenter.getDataExternal(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchoffline, container, false);
        initViews(view);
        handleEvents();
        return view;
    }

    private void initViews(View view) {
        mEditText = getActivity().findViewById(R.id.edit_search);
        mImageViewCancel = getActivity().findViewById(R.id.image_cancel);
        mRecyclerSong = view.findViewById(R.id.recycler_song);
        mRecyclerArtist = view.findViewById(R.id.recycler_artist);
        mRecyclerAlbum = view.findViewById(R.id.recycler_album);
        mTxtLabelAlbum = view.findViewById(R.id.text_label_album);
        mTxtLabelArtist = view.findViewById(R.id.text_label_artist);
        mTxtLabelSong = view.findViewById(R.id.text_label_song);
        mTxtNoData = view.findViewById(R.id.text_no_data);

        LinearLayoutManager songManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager albumManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager artistManager = new LinearLayoutManager(getActivity());
        //song
        mSearchSongRecyclerAdapter = new SearchSongRecyclerAdapter(getActivity());
        mRecyclerSong.setLayoutManager(songManager);
        mRecyclerSong.setAdapter(mSearchSongRecyclerAdapter);
        mSearchSongRecyclerAdapter.setTracksTemp(mPresenter.getSongList());
        mSearchSongRecyclerAdapter.setOnItemSongClickListener(this);
        //album
        mSearchAlbumRecyclerAdapter = new SearchAlbumRecyclerAdapter(getActivity());
        mRecyclerAlbum.setLayoutManager(albumManager);
        mRecyclerAlbum.setAdapter(mSearchAlbumRecyclerAdapter);
        mSearchAlbumRecyclerAdapter.setOnItemAlbumClickListener(this);
        //artist
        mSearchArtistRecyclerAdapter = new SearchArtistRecyclerAdapter(getActivity());
        mRecyclerArtist.setLayoutManager(artistManager);
        mRecyclerArtist.setAdapter(mSearchArtistRecyclerAdapter);
        mSearchArtistRecyclerAdapter.setOnItemArtistClickListener(this);
    }

    private void handleEvents() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mEditText.getText().toString().isEmpty()) {
                    mPresenter.doPassKeyWord();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    @Override
    public void onGetListTrackSuccess(List<Track> track) {
        if (track == null) {
            return;
        }
        mTxtLabelSong.setVisibility(View.VISIBLE);
        List<Track> list = new ArrayList<>();
        updateSearchTrackUI(list, track);
    }

    private void updateSearchTrackUI(List<Track> list, List<Track> track) {
        if (track == null) {
            mTxtLabelSong.setVisibility(View.GONE);
            return;
        }
        mTxtLabelSong.setVisibility(View.VISIBLE);
        mTxtNoData.setVisibility(View.GONE);
        if (track.size() <= SEARCH_AMOUNT) {
            mSearchSongRecyclerAdapter.updateData(track);
        } else {
            for (int i = 0; i < SEARCH_AMOUNT; i++) {
                list.add(track.get(i));
            }
            mSearchSongRecyclerAdapter.updateData(list);
        }
    }

    @Override
    public void onGetListAlbumSuccess(List<Album> album) {
        if (album == null) {
            return;
        }
        mTxtLabelAlbum.setVisibility(View.VISIBLE);
        List<Album> list = new ArrayList<>();
        updateSearchAlbumUI(list, album);
    }

    private void updateSearchAlbumUI(List<Album> list, List<Album> album) {
        if (album == null) {
            mTxtLabelAlbum.setVisibility(View.GONE);
            return;
        }
        mTxtLabelAlbum.setVisibility(View.VISIBLE);
        mTxtNoData.setVisibility(View.GONE);
        if (album.size() <= SEARCH_AMOUNT) {
            mSearchAlbumRecyclerAdapter.updateData(album);
        } else {
            for (int i = 0; i < SEARCH_AMOUNT; i++) {
                list.add(album.get(i));
            }
            mSearchAlbumRecyclerAdapter.updateData(list);
        }
    }

    @Override
    public void onGetListArtistSuccess(List<Artist> artist) {
        if (artist == null) {
            return;
        }
        mTxtLabelArtist.setVisibility(View.VISIBLE);
        List<Artist> list = new ArrayList<>();
        updateSearchArtistUI(list, artist);
    }

    private void updateSearchArtistUI(List<Artist> list, List<Artist> artist) {
        if (artist == null) {
            mTxtLabelArtist.setVisibility(View.GONE);
            return;
        }
        mTxtLabelArtist.setVisibility(View.VISIBLE);
        mTxtNoData.setVisibility(View.GONE);
        if (artist.size() <= SEARCH_AMOUNT) {
            mSearchArtistRecyclerAdapter.updateData(artist);
        } else {
            for (int i = 0; i < SEARCH_AMOUNT; i++) {
                list.add(artist.get(i));
            }
            mSearchArtistRecyclerAdapter.updateData(list);
        }
    }

    @Override
    public void onGetListArtistFail() {
        mTxtLabelArtist.setVisibility(View.GONE);
        mSearchArtistRecyclerAdapter.clearData();
    }

    @Override
    public void onGetListAlbumFail() {
        mTxtLabelAlbum.setVisibility(View.GONE);
        mSearchAlbumRecyclerAdapter.clearData();
    }

    @Override
    public void onGetListTrackFail() {
        mTxtLabelSong.setVisibility(View.GONE);
        mSearchSongRecyclerAdapter.clearData();
    }

    @Override
    public void onFetchAllDataSuccess(int countFail) {
        if (countFail >= 3) {
            mTxtLabelArtist.setVisibility(View.GONE);
            mTxtLabelAlbum.setVisibility(View.GONE);
            mTxtLabelSong.setVisibility(View.GONE);
            mSearchSongRecyclerAdapter.clearData();
            mSearchAlbumRecyclerAdapter.clearData();
            mSearchArtistRecyclerAdapter.clearData();
            mTxtNoData.setVisibility(View.VISIBLE);
        } else {
            mTxtNoData.setVisibility(View.GONE);
        }
    }

    @Override
    public String getEditText() {
        return mEditText.getText().toString();
    }

    @Override
    public void onItemSongClicked(String nameSong, String nameArtist, List<Track> tracksTemp) {
        int position = 0;
        for (Track track : tracksTemp) {
            if (nameSong.equals(track.getName()) && nameArtist.equals(track.getNameArtist())) {
                position = track.getPosition();
            }
        }
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(Constant.EXTRA_TRACK_LIST_ITEM,
                (ArrayList<? extends Parcelable>) tracksTemp).putExtra(Constant.EXTRA_ID, position);
        getActivity().setResult(Constant.RESULT_CODE, intent);
        getActivity().finish();
    }

    @Override
    public void onItemAlbumClicked(String name) {
        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_NAME_ALBUM, name);
        getActivity().setResult(Constant.RESULT_CODE_ALBUM, intent);
        getActivity().finish();
    }

    @Override
    public void onItemArtistClicked(String name) {
        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_NAME_ARTIST, name);
        getActivity().setResult(Constant.RESULT_CODE_ARTIST, intent);
        getActivity().finish();
    }
}
