package com.fstyle.androidtrainning.screen.main;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.model.SearchAlbum;
import com.fstyle.androidtrainning.model.SearchTrack;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BaseActivity
        implements MainContract.Viewer, OnTabSelectListener, SearchView.OnQueryTextListener {

    private BottomBar mBottomBar;
    private TextView mTxtLabelSong, mTxtLabelAlbum, mTxtLabelArtist, mTxtNoData;
    private MainPresenter mPresenter;
    private MainViewPagerAdapter mMainPagerAdapter;
    private PlayingViewPagerAdapter mPlayingPagerAdapter;
    private ViewPager mMainViewPager, mPlayingViewPager;
    private SearchView mSearchView;
    private CircleIndicator mIndicator;
    private LastFmApi mApi;
    private LinearLayout mLayoutPlaying;
    private ScrollView mLayoutSearch;
    private RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerSong, mRecyclerAlbum, mRecyclerArtist;
    private SearchSongRecyclerAdapter mSearchSongRecyclerAdapter;
    private SearchAlbumRecyclerAdapter mSearchAlbumRecyclerAdapter;
    private SearchArtistRecyclerAdapter mSearchArtistRecyclerAdapter;
    private static final int SEARCH_AMOUNT = 4;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        mBottomBar = findViewById(R.id.bottomBar);
        mTxtLabelAlbum = findViewById(R.id.text_label_album);
        mTxtLabelArtist = findViewById(R.id.text_label_artist);
        mTxtLabelSong = findViewById(R.id.text_label_song);
        mTxtNoData = findViewById(R.id.text_no_data);
        mMainViewPager = findViewById(R.id.viewPager);
        mPlayingViewPager = findViewById(R.id.pager_playing);
        mLayoutPlaying = findViewById(R.id.layout_playing);
        mLayoutSearch = findViewById(R.id.layout_search);
        mRelativeLayout = findViewById(R.id.layout_main);
        mIndicator = findViewById(R.id.idicator);
        mRecyclerSong = findViewById(R.id.recycler_song);
        mRecyclerArtist = findViewById(R.id.recycler_artist);
        mRecyclerAlbum = findViewById(R.id.recycler_album);

        mApi = MainApplication.getLastFmApi();
        mBottomBar.setOnTabSelectListener(this);

        mPresenter = new MainPresenter();
        mPresenter.setApi(mApi);
        mPresenter.setView(this);

        mMainPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mPlayingPagerAdapter = new PlayingViewPagerAdapter(getSupportFragmentManager());
        mMainViewPager.setAdapter(mMainPagerAdapter);
        mPlayingViewPager.setAdapter(mPlayingPagerAdapter);
        mIndicator.setViewPager(mPlayingViewPager);
        mPlayingPagerAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());

        LinearLayoutManager songManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager albumhManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager artistManager = new LinearLayoutManager(getApplicationContext());
        //song
        mSearchSongRecyclerAdapter = new SearchSongRecyclerAdapter(this);
        mRecyclerSong.setLayoutManager(songManager);
        mRecyclerSong.setAdapter(mSearchSongRecyclerAdapter);
        //album
        mSearchAlbumRecyclerAdapter = new SearchAlbumRecyclerAdapter(this);
        mRecyclerAlbum.setLayoutManager(albumhManager);
        mRecyclerAlbum.setAdapter(mSearchAlbumRecyclerAdapter);
        //artist
        mSearchArtistRecyclerAdapter = new SearchArtistRecyclerAdapter(this);
        mRecyclerArtist.setLayoutManager(artistManager);
        mRecyclerArtist.setAdapter(mSearchArtistRecyclerAdapter);
    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tab_mysongs:
                mMainViewPager.setCurrentItem(Tab.MY_SONG);
                break;
            case R.id.tab_online:
                mMainViewPager.setCurrentItem(Tab.ONLINE);
                break;
            case R.id.tab_another:
                mMainViewPager.setCurrentItem(Tab.ANOTHER);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem item = menu.findItem(R.id.search_view);
        mSearchView = (SearchView) item.getActionView();

        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.equals("")) {
            mPresenter.doPassKeyWord(newText);
            mLayoutSearch.setVisibility(View.VISIBLE);
            mRelativeLayout.setVisibility(View.GONE);
            mLayoutPlaying.setVisibility(View.GONE);
        } else {
            mTxtNoData.setVisibility(View.GONE);
            mLayoutSearch.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.VISIBLE);
            mLayoutPlaying.setVisibility(View.GONE);
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onListTrackSuccess(List<SearchTrack> track) {
        List<SearchTrack> list = new ArrayList<>();
        updateSearchTrackUI(list, track);
    }

    private void updateSearchTrackUI(List<SearchTrack> list, List<SearchTrack> track) {
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
    public void onListAlbumSuccess(List<SearchAlbum> album) {
        List<SearchAlbum> list = new ArrayList<>();
        updateSearchAlbumUI(list, album);
    }

    private void updateSearchAlbumUI(List<SearchAlbum> list, List<SearchAlbum> album) {
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
    public void onListArtistSuccess(List<Artist> artist) {
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
    public void onListArtistFail() {
        mTxtLabelArtist.setVisibility(View.GONE);
        mSearchArtistRecyclerAdapter.clearData();
    }

    @Override
    public void onListAlbumFail() {
        mTxtLabelAlbum.setVisibility(View.GONE);
        mSearchAlbumRecyclerAdapter.clearData();
    }

    @Override
    public void onListTrackFail() {
        mTxtLabelSong.setVisibility(View.GONE);
        mSearchSongRecyclerAdapter.clearData();
    }

    @Override
    public void onFetchAllDataSuccess(boolean isFetchDataSuccess) {
        if (!isFetchDataSuccess) {
            mTxtLabelArtist.setVisibility(View.GONE);
            mTxtLabelAlbum.setVisibility(View.GONE);
            mTxtLabelSong.setVisibility(View.GONE);
            mSearchSongRecyclerAdapter.clearData();
            mSearchAlbumRecyclerAdapter.clearData();
            mSearchArtistRecyclerAdapter.clearData();
            mTxtNoData.setVisibility(View.VISIBLE);
        } else {
            mTxtLabelArtist.setVisibility(View.VISIBLE);
            mTxtLabelAlbum.setVisibility(View.VISIBLE);
            mTxtLabelSong.setVisibility(View.VISIBLE);
            mTxtNoData.setVisibility(View.GONE);
        }
    }

    @IntDef({ Tab.MY_SONG, Tab.ONLINE, Tab.ANOTHER })
    public @interface Tab {
        int MY_SONG = 0;
        int ONLINE = 1;
        int ANOTHER = 2;
    }
}
