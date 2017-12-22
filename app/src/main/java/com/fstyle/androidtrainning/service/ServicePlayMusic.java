package com.fstyle.androidtrainning.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import java.util.List;

public class ServicePlayMusic extends Service{

    private List<Integer> mLenght;
    private MediaPlayer mMediaPlayer;
    private Uri mUri;
    private ExternalData mExternalData;
    private int mediaLenght = 0;
    private int position = 0;

    public ServicePlayMusic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
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

    public void playMusic(){
        mMediaPlayer.start();
    }

    public void pauseMusic(){
        if (mMediaPlayer.isPlaying()){
            mediaLenght = mMediaPlayer.getCurrentPosition();
            mMediaPlayer.pause();
        }
    }

    public void forwardMusic(){
        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = MediaPlayer.create(this, mLenght.get(position));
            mMediaPlayer.start();
        }else {
            mMediaPlayer = MediaPlayer.create(this, mLenght.get(position));
            mMediaPlayer.start();
        }
    }

    public void seekMusic(){
        mMediaPlayer.seekTo(mediaLenght);
    }

    public void stopMusic(){
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}
