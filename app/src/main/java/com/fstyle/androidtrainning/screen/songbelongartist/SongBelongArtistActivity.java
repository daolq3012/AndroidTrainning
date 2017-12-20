package com.fstyle.androidtrainning.screen.songbelongartist;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.utils.Constant;

/**
 * Songbelongartist Screen.
 */
public class SongBelongArtistActivity extends BaseActivity
        implements SongBelongArtistContract.Viewer {

    SongBelongArtistContract.Presenter mPresenter;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImageViewDetail;
    private ExternalData mExternalData = new ExternalData();
    private RecyclerView mRecyclerView;
    private SongBelongArtistRecyclerAdapter mRecyclerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songbelongartist);

        initViews();
    }

    private void initViews() {
        mPresenter = new SongBelongArtistPresenter();
        mPresenter.setView(this);
        mImageViewDetail = findViewById(R.id.image_detail);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getBackPress();
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mRecyclerView = findViewById(R.id.recycler_song);
        mRecyclerAdapter = new SongBelongArtistRecyclerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //updateData
        mRecyclerAdapter.updateData(mExternalData.getArrayListTrackBelongArtist());

        //setUI
        String nameArtist = getIntent().getStringExtra(Constant.EXTRA_NAME_ARTIST);
        scanData(nameArtist);

        mCollapsingToolbarLayout.setTitle(nameArtist);
        mCollapsingToolbarLayout.setExpandedTitleColor(
                getResources().getColor(R.color.color_white));
    }

    private void scanData(String nameArtist) {
        mExternalData.scanAllMusicBelongArtist(this, nameArtist);
    }

    private void getBackPress() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
}
