package com.fstyle.androidtrainning.screen.main;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong
        .OnItemFavoriteClickListener;
import com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong
        .OnItemListSongClickListener;
import com.fstyle.androidtrainning.screen.main.playingfragments.listsong
        .OnItemSubListSongClickListener;
import com.fstyle.androidtrainning.screen.search.SearchActivity;
import com.fstyle.androidtrainning.screen.songbelongalbum.SongBelongAlbumActivity;
import com.fstyle.androidtrainning.screen.songbelongartist.SongBelongArtistActivity;
import com.fstyle.androidtrainning.service.ServicePlayMusic;
import com.fstyle.androidtrainning.utils.Constant;
import com.fstyle.androidtrainning.utils.Utilities;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BaseActivity
        implements MainContract.Viewer, OnTabSelectListener, View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, OnItemListSongClickListener, OnItemFavoriteClickListener,
        OnItemSubListSongClickListener {

    private BottomBar mBottomBar;
    private MainPresenter mPresenter;
    private MainViewPagerAdapter mMainPagerAdapter;
    private PlayingViewPagerAdapter mPlayingPagerAdapter;
    private ViewPager mMainViewPager, mPlayingViewPager;
    private CircleIndicator mIndicator;
    private RelativeLayout mLayoutMain;
    private LinearLayout mLayoutPlaying, mLayoutMiniPlaying;
    private LastFmApi mApi;
    private Toolbar mToolbar;
    private ImageView mImageBack, mImagePause, mImagePrevious, mImageNext, mImageRepeat,
            mImageShuffle, mImagePreviousMini, mImageNextMini, mImagePauseMini, mImageAlarm;
    private SeekBar mSeekBarTime;
    private TextView mTxtTextStart, mTxtTextTotal, mTxtNameSong, mTxtArtist, mTxtNameSongMini,
            mTxtArtistMini;
    private ServicePlayMusic mServicePlayMusic;
    private static final String TAG = "MainActivity";
    private boolean isConnect;
    private boolean isMusicPlaying = false;
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    private Handler mHandler = new Handler();
    private Handler mHandlerAlarm = new Handler();
    private Utilities mUtilities = new Utilities();
    private Track mTrack;
    private List<Track> mTrackList = new ArrayList<>();
    private OnTrackChangeListener mOnTrackChangeListener;
    public static final int ALARM_TIME = 120;
    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 15;
    private int mTimeToCd = 0, mCdTime = 0;
    private boolean mIsPause = false;
    private Intent intent;
    private BroadcastReceiver mBroadcastReceiver;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnect = true;
            //get music service
            ServicePlayMusic.LocalBinder binder = (ServicePlayMusic.LocalBinder) service;
            mServicePlayMusic = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        connectService();

        showCountDown();

        doRegisterNotificationSign();

        initListeners();
    }

    private void connectService() {
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void startService(int position, List<Track> tracks) {
        intent.putParcelableArrayListExtra(Constant.EXTRA_TRACK_LIST_ITEM,
                (ArrayList<? extends Parcelable>) tracks).putExtra(Constant.EXTRA_ID, position);
        startService(intent);
    }

    private void initListeners() {
        mSeekBarTime.setOnSeekBarChangeListener(this);
        mImageNext.setOnClickListener(this);
        mImagePrevious.setOnClickListener(this);
        mImagePause.setOnClickListener(this);
        mImageShuffle.setOnClickListener(this);
        mImageRepeat.setOnClickListener(this);
        mImageNextMini.setOnClickListener(this);
        mImagePauseMini.setOnClickListener(this);
        mImagePreviousMini.setOnClickListener(this);
        mImageBack.setOnClickListener(this);
        mBottomBar.setOnTabSelectListener(this);
        mLayoutMiniPlaying.setOnClickListener(this);
        mImageAlarm.setOnClickListener(this);
    }

    private void initViews() {
        mToolbar = findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        mBottomBar = findViewById(R.id.bottomBar);
        mMainViewPager = findViewById(R.id.viewPager);
        mLayoutMain = findViewById(R.id.layout_main);
        mLayoutPlaying = findViewById(R.id.layout_playing);
        mLayoutMiniPlaying = findViewById(R.id.layout_mini_playing);
        mPlayingViewPager = findViewById(R.id.pager_playing);
        mIndicator = findViewById(R.id.idicator);
        mImageBack = findViewById(R.id.image_back);
        mImagePause = findViewById(R.id.image_pause);
        mSeekBarTime = findViewById(R.id.seekBar);
        mTxtTextStart = findViewById(R.id.text_start);
        mTxtTextTotal = findViewById(R.id.text_total);
        mTxtNameSong = findViewById(R.id.text_name_song);
        mTxtArtist = findViewById(R.id.text_artist);
        mImageNext = findViewById(R.id.image_next);
        mImagePrevious = findViewById(R.id.image_previous);
        mImageRepeat = findViewById(R.id.image_repeat);
        mImageShuffle = findViewById(R.id.image_shuffle);
        mImageNextMini = findViewById(R.id.image_next_mini);
        mImagePauseMini = findViewById(R.id.image_pause_mini);
        mImagePreviousMini = findViewById(R.id.image_previous_mini);
        mTxtArtistMini = findViewById(R.id.text_name_artist_mini);
        mTxtNameSongMini = findViewById(R.id.text_name_song_mini);
        mImageAlarm = findViewById(R.id.image_alarm);

        mApi = MainApplication.getLastFmApi();

        mPresenter = new MainPresenter();
        mPresenter.setApi(mApi);
        mPresenter.setView(this);

        intent = new Intent(this, ServicePlayMusic.class);

        mMainPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mPlayingPagerAdapter = new PlayingViewPagerAdapter(getSupportFragmentManager());
        mMainViewPager.setAdapter(mMainPagerAdapter);
        mPlayingViewPager.setAdapter(mPlayingPagerAdapter);
        mIndicator.setViewPager(mPlayingViewPager);
        mPlayingPagerAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());
    }

    public void setOnTrackChangeListener(OnTrackChangeListener onTrackChangeListener) {
        mOnTrackChangeListener = onTrackChangeListener;
    }

    private void doRegisterNotificationSign() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case Constant.ACTION_NOTIFICATION_PAUSE:
                        if (!isMusicPlaying) {
                            isMusicPlaying = !isMusicPlaying;
                            mImagePause.setImageResource(R.drawable.ic_play);
                        } else {
                            isMusicPlaying = !isMusicPlaying;
                            mImagePause.setImageResource(R.drawable.ic_pause);
                        }
                        break;
                    case Constant.ACTION_NOTIFICATION_CLOSE:
                        if (!isMusicPlaying) {
                            isMusicPlaying = !isMusicPlaying;
                            mImagePause.setImageResource(R.drawable.ic_play);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        registerReceiver(mBroadcastReceiver, new IntentFilter(Constant.ACTION_NOTIFICATION_CLOSE));
        registerReceiver(mBroadcastReceiver, new IntentFilter(Constant.ACTION_NOTIFICATION_PAUSE));
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
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                startActivityForResult(new Intent(MainActivity.this, SearchActivity.class),
                        Constant.REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Track getTrack() {
        return mTrack;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        if (isConnect) {
            unbindService(mConnection);
            isConnect = false;
        }

        super.onStop();
    }

    private int getProgressMedia() {
        if (mServicePlayMusic == null) {
            return 0;
        }
        return mServicePlayMusic.getMediaPlayer().getDuration();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                mLayoutPlaying.setVisibility(View.GONE);
                mLayoutMain.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_mini_playing:
                mLayoutMain.setVisibility(View.GONE);
                mLayoutPlaying.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.GONE);
                break;
            case R.id.image_pause_mini:
                if (isMusicPlaying) {
                    isMusicPlaying = !isMusicPlaying;
                    changePauseImage();
                    mServicePlayMusic.resumeMusic();
                    //                    mSeekBarTime.setMax(getProgressMedia());
                } else {
                    isMusicPlaying = !isMusicPlaying;
                    changePlayImage();
                    mServicePlayMusic.pauseMusic();
                }
                break;
            case R.id.image_pause:
                if (isMusicPlaying) {
                    isMusicPlaying = !isMusicPlaying;
                    changePauseImage();
                    mServicePlayMusic.resumeMusic();
                    //                    mSeekBarTime.setMax(getProgressMedia());
                } else {
                    isMusicPlaying = !isMusicPlaying;
                    changePlayImage();
                    mServicePlayMusic.pauseMusic();
                }
                break;
            case R.id.image_previous_mini:
                if (isMusicPlaying) {
                    mSeekBarTime.setMax(getProgressMedia());
                } else {
                    changePauseImage();
                    isMusicPlaying = false;
                }
                mServicePlayMusic.previousTrack();
                mTrack = mServicePlayMusic.getTrack();
                setNameSongArtist(mTrack);
                mOnTrackChangeListener.onTrackChanged();
                break;
            case R.id.image_previous:
                if (isMusicPlaying) {
                    mSeekBarTime.setMax(getProgressMedia());
                } else {
                    changePauseImage();
                    isMusicPlaying = false;
                }
                mServicePlayMusic.previousTrack();
                mTrack = mServicePlayMusic.getTrack();
                setNameSongArtist(mTrack);
                mOnTrackChangeListener.onTrackChanged();
                break;
            case R.id.image_next_mini:
                if (isMusicPlaying) {
                    mSeekBarTime.setMax(getProgressMedia());
                } else {
                    changePauseImage();
                    isMusicPlaying = false;
                }
                mServicePlayMusic.nextTrack();
                mTrack = mServicePlayMusic.getTrack();
                setNameSongArtist(mTrack);
                mOnTrackChangeListener.onTrackChanged();
                break;
            case R.id.image_next:
                //                doNextSong();
                if (isMusicPlaying) {
                    mSeekBarTime.setMax(getProgressMedia());
                } else {
                    changePauseImage();
                    isMusicPlaying = false;
                }
                mServicePlayMusic.nextTrack();
                mTrack = mServicePlayMusic.getTrack();
                setNameSongArtist(mTrack);
                mOnTrackChangeListener.onTrackChanged();
                break;
            case R.id.image_repeat:
                if (isRepeat) {
                    isRepeat = false;
                    mServicePlayMusic.setRepeat(false);
                    mImageRepeat.setImageResource(R.drawable.ic_repeat);
                } else {
                    // make repeat to true
                    isRepeat = true;
                    mServicePlayMusic.setRepeat(true);
                    mImageRepeat.setImageResource(R.drawable.ic_repeat_one);
                }
                break;
            case R.id.image_shuffle:
                if (isShuffle) {
                    isShuffle = false;
                    mImageShuffle.setImageResource(R.drawable.ic_shuffle);
                    mServicePlayMusic.setShuffle(false);
                } else {
                    // make repeat to true
                    isShuffle = true;
                    mServicePlayMusic.setShuffle(true);
                    // make shuffle to false
                    mImageShuffle.setImageResource(R.drawable.ic_not_shuffle);
                }
                break;
            case R.id.image_alarm:
                initAlarm();
                break;
            default:
                break;
        }
    }

    private void initAlarm() {
        Dialog dialog = new Dialog(this);
        // khởi tạo dialog
        dialog.setContentView(R.layout.layout_alarm_dialog);
        // xét layout cho dialog
        dialog.setTitle(getResources().getString(R.string.alarm_text));
        final SeekBar sbAlarm = dialog.findViewById(R.id.seekBar_alarm);
        final TextView tvAlarm = dialog.findViewById(R.id.text_alarm);
        sbAlarm.setMax(ALARM_TIME);
        sbAlarm.setProgress(mTimeToCd);
        final String shutdown = getResources().getString(R.string.shutdown_after);
        final String min = getResources().getString(R.string.min);
        String notification = shutdown + mTimeToCd + min;
        tvAlarm.setText(notification);
        sbAlarm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                String notification = shutdown + progress + min;
                tvAlarm.setText(notification);
                mTimeToCd = progress;
                mCdTime = mTimeToCd * 60;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // xét tiêu đề cho dialog

        dialog.show();
    }

    private Runnable countDownHandle = new Runnable() {
        @Override
        public void run() {
            mCdTime--;
            mTimeToCd = mCdTime / 60;
            if (mCdTime == 0) {
                isMusicPlaying = !isMusicPlaying;
                changePlayImage();
                mServicePlayMusic.pauseMusic();
                mHandlerAlarm.removeCallbacks(countDownHandle);
                return;
            }
            mHandlerAlarm.postDelayed(countDownHandle, 1000);
        }
    };

    private void showCountDown() {
        mHandlerAlarm.removeCallbacks(countDownHandle);
        mHandlerAlarm.postDelayed(countDownHandle, 1000);
    }

    private void changePauseImage() {
        mImagePause.setImageResource(R.drawable.ic_pause);
        mImagePauseMini.setImageResource(R.drawable.ic_pause);
    }

    private void changePlayImage() {
        mImagePause.setImageResource(R.drawable.ic_play);
        mImagePauseMini.setImageResource(R.drawable.ic_play);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mServicePlayMusic.setMediaLength(progress);
            mServicePlayMusic.seekMusic();
        } else {
            updateUI();
        }
    }

    private void updateUI() {
        mHandler.removeCallbacks(setupUI);
        mHandler.postDelayed(setupUI, 1000);
    }

    private Runnable setupUI = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void run() {
            if (mServicePlayMusic.getMediaPlayer() == null) {
                return;
            }
            int currentPosition = mServicePlayMusic.getMediaPlayer().getCurrentPosition() + 1;
            int totalPosition = mServicePlayMusic.getMediaPlayer().getDuration();
            String currentTime = mUtilities.milliSecondsToTimer(currentPosition);
            String totalTime = mUtilities.milliSecondsToTimer(totalPosition);
            mTxtTextStart.setText(currentTime);
            mTxtTextTotal.setText(totalTime);
            mSeekBarTime.setMax(totalPosition);
            mSeekBarTime.setProgress(currentPosition);
            mTrack = mServicePlayMusic.getTrack();
            setNameSongArtist(mTrack);
            if (currentTime.equals(totalTime)) {
                mSeekBarTime.setProgress(0);
                mServicePlayMusic.nextTrack();
                mSeekBarTime.setMax(totalPosition);
                mTxtTextTotal.setText(totalTime);
                changePauseImage();
            }
            mHandler.postDelayed(setupUI, 1000);
        }
    };

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setUIAndService(int position, List<Track> tracks) {
        mLayoutMain.setVisibility(View.GONE);
        mLayoutMiniPlaying.setVisibility(View.VISIBLE);
        mLayoutPlaying.setVisibility(View.VISIBLE);
        mToolbar.setVisibility(View.GONE);
        if (!isMusicPlaying) {
            changePauseImage();
            isMusicPlaying = false;
        } else {
            changePlayImage();
            isMusicPlaying = true;
        }
        if (mTrackList != null) {
            mTrackList.clear();
        }
        mTrackList.addAll(tracks);
        mTrack = tracks.get(position);
        setNameSongArtist(mTrack);
        if (mServicePlayMusic.getMediaPlayer() != null) {
            mSeekBarTime.setMax(getProgressMedia());
        }
        startService(position, tracks);
        if (mOnTrackChangeListener == null) {
            return;
        }
        mOnTrackChangeListener.onTrackChanged();
    }

    private void setNameSongArtist(Track track) {
        if (track.getName().length() >= MAX_LENGTH) {
            String subName = track.getName().substring(MIN_LENGTH, MAX_LENGTH) + "...";
            mTxtNameSong.setText(subName);
            mTxtNameSongMini.setText(subName);
        } else {
            mTxtNameSong.setText(track.getName());
            mTxtNameSongMini.setText(track.getName());
        }
        if (track.getNameArtist().length() >= MAX_LENGTH) {
            String subArtist = track.getNameArtist().substring(MIN_LENGTH, MAX_LENGTH) + "...";
            mTxtArtist.setText(subArtist);
            mTxtArtistMini.setText(subArtist);
        } else {
            mTxtArtist.setText(track.getNameArtist());
            mTxtArtistMini.setText(track.getNameArtist());
        }
    }

    @Override
    public void onItemClicked(int position, List<Track> tracks) {
        setUIAndService(position, tracks);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE && resultCode == Constant.RESULT_CODE) {
            if (data == null) {
                return;
            }
            List<Track> tracks = data.getParcelableArrayListExtra(Constant.EXTRA_TRACK_LIST_ITEM);
            int position = data.getIntExtra(Constant.EXTRA_ID, 0);
            setUIAndService(position, tracks);
        } else if (requestCode == Constant.REQUEST_CODE
                && resultCode == Constant.RESULT_CODE_ALBUM) {
            if (data == null) {
                return;
            }
            startActivityForResult(
                    new Intent(MainActivity.this, SongBelongAlbumActivity.class).putExtra(
                            Constant.EXTRA_NAME_ALBUM,
                            data.getStringExtra(Constant.EXTRA_NAME_ALBUM)), Constant.REQUEST_CODE);
        } else if (requestCode == Constant.REQUEST_CODE
                && resultCode == Constant.RESULT_CODE_ARTIST) {
            if (data == null) {
                return;
            }
            startActivityForResult(
                    new Intent(MainActivity.this, SongBelongArtistActivity.class).putExtra(
                            Constant.EXTRA_NAME_ARTIST,
                            data.getStringExtra(Constant.EXTRA_NAME_ARTIST)),
                    Constant.REQUEST_CODE);
        }
    }

    @Override
    public void onItemFavClicked(int position, List<TrackEntity> tracks) {
        if (tracks == null) {
            return;
        }
        List<Track> trackList = new ArrayList<>();
        for (TrackEntity track : tracks) {
            Track track1 = new Track();
            track1.setName(track.getNameSong());
            track1.setNameArtist(track.getNameArtist());
            track1.setTrackData(track.getPath());
            track1.setPosition(track.getId());
            trackList.add(track1);
        }
        setUIAndService(position, trackList);
    }

    public List<Track> getTrackList() {
        return mTrackList;
    }

    public void setTrackList(List<Track> trackList) {
        mTrackList = trackList;
    }

    @Override
    public void onItemSubListSongClicked(int position) {
        setUI(position, mTrackList);
    }

    private void setUI(int position, List<Track> trackList) {
        if (!isMusicPlaying) {
            changePauseImage();
            isMusicPlaying = false;
        } else {
            changePlayImage();
            isMusicPlaying = true;
        }
        mTrack = trackList.get(position);
        setNameSongArtist(mTrack);
        if (mServicePlayMusic.getMediaPlayer() != null) {
            mSeekBarTime.setMax(getProgressMedia());
        }
        startService(position, trackList);
        if (mOnTrackChangeListener == null) {
            return;
        }
        mOnTrackChangeListener.onTrackChanged();
    }

    @IntDef({ Tab.MY_SONG, Tab.ONLINE, Tab.ANOTHER })
    public @interface Tab {
        int MY_SONG = 0;
        int ONLINE = 1;
        int ANOTHER = 2;
    }

    @Override
    protected void onDestroy() {
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }
        super.onDestroy();
    }
}
