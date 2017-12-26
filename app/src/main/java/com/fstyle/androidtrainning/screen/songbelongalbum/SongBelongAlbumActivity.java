package com.fstyle.androidtrainning.screen.songbelongalbum;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.utils.Constant;
import java.util.ArrayList;
import java.util.List;

/**
 * Songbelongalbum Screen.
 */
public class SongBelongAlbumActivity extends BaseActivity
        implements SongBelongAlbumContract.Viewer, OnItemClickListener {

    private SongBelongAlbumContract.Presenter mPresenter;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImageViewDetail;
    private ExternalData mExternalData = new ExternalData();
    private RecyclerView mRecyclerView;
    private SongBelongAlbumRecyclerAdapter mRecyclerAdapter;
    private Toolbar mToolbar;
    private String nameAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songbelongalbum);
        initViews();
    }

    private void initViews() {
        mPresenter = new SongBelongAlbumPresenter();
        mPresenter.setView(this);
        mImageViewDetail = findViewById(R.id.image_detail);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getBackPress();
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mRecyclerView = findViewById(R.id.recycler_song);
        mRecyclerAdapter = new SongBelongAlbumRecyclerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setOnItemClickListener(this);

        //updateData
        mRecyclerAdapter.updateData(mExternalData.getArrayListTrackBelongAlbum());

        //setUI
        nameAlbum = getIntent().getStringExtra(Constant.EXTRA_NAME_ALBUM);
        scanData(nameAlbum);

        loadImage(nameAlbum);

        mCollapsingToolbarLayout.setTitle(nameAlbum);
        mCollapsingToolbarLayout.setExpandedTitleColor(
                getResources().getColor(R.color.color_white));
    }

    private void getBackPress() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Constant.RESULT_CODE);
                onBackPressed();
            }
        });
    }

    private void loadImage(String nameAlbum) {
        for (Album album : mExternalData.getArrayListAlbum()) {
            if (album.getName().equals(nameAlbum)) {
                mImageViewDetail.setImageBitmap(album.getBmAlbum());
                break;
            }
        }
    }

    private void scanData(String nameAlbum) {
        mExternalData.scanAllMusicBelongAlbum(this, nameAlbum);
        mExternalData.scanAllAlbum(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onItemClicked(int position, List<Track> tracks) {
        Intent intent = getIntent();
        intent.putParcelableArrayListExtra(Constant.EXTRA_TRACK_LIST_ITEM,
                (ArrayList<? extends Parcelable>) tracks).putExtra(Constant.EXTRA_ID, position);
        setResult(Constant.RESULT_CODE, intent);
        finish();
    }
}
