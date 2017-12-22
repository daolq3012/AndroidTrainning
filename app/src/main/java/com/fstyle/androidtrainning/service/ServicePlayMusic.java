package com.fstyle.androidtrainning.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.utils.Constant;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class ServicePlayMusic extends Service {

    private MediaPlayer mMediaPlayer;
    private Uri mUri;
    private ExternalData mExternalData = new ExternalData();
    private int id = 0;
    private int mediaLength = 0;
    private IBinder mIBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public ServicePlayMusic getService() {
            return ServicePlayMusic.this;
        }
    }

    public ServicePlayMusic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Track track = intent.getParcelableExtra(Constant.EXTRA_TRACK_ITEM);
        id = track.getPosition();
        playMusic();
        return mIBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mExternalData.scanAllMusic(this);
        mExternalData.scanAllAlbum(this);
        mExternalData.scanAllArtist(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.release();
        super.onDestroy();
    }

    private void createMusic() {
        try {
            String path = mExternalData.getArrayListTrack().get(id).getTrackData();
            mUri = Uri.parse(path);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            MediaPlayer.create(getApplicationContext(), mUri);
        } catch (IOException e) {
            Log.e(TAG, "createMusic: ", e);
        }
    }

    public void playMusic() {
        createMusic();
        mMediaPlayer.start();
    }

    public void pauseMusic() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }

    public void nextTrack() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        id++;
        if (id < mExternalData.getArrayListTrack().size()) {
            playMusic();
        } else {
            id = 0;
            playMusic();
        }
    }

    public void previousTrack() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        id--;
        if (id >= 0) {
            playMusic();
        } else {
            id = mExternalData.getArrayListTrack().size() - 1;
            playMusic();
        }
    }

    public void seekMusic() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.seekTo(mediaLength);
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public int getMediaLength() {
        return mediaLength;
    }

    public void setMediaLength(int mediaLength) {
        this.mediaLength = mediaLength;
    }
}