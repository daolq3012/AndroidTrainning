package com.fstyle.androidtrainning.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.RemoteViews;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.sharedpreference.SharedPreference;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.splash.SplashActivity;
import com.fstyle.androidtrainning.utils.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class ServicePlayMusic extends Service {

    private MediaPlayer mMediaPlayer;
    private int id = 0;
    private int mediaLength = 0;
    private IBinder mIBinder = new LocalBinder();
    private Handler mHandler = new Handler();
    private List<Track> mTracks = new ArrayList<>();
    private boolean mIsShuffle = false;
    private boolean mIsRepeat = false;
    private boolean mIsPause = false;
    private BroadcastReceiver mBroadcastReceiver;
    private static final int NOTIFICATION_ID = 101;
    private Notification mNotification;
    private RemoteViews mRemoteViews;
    private NotificationManager notificationManager;
    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 15;
    private SharedPreference mPreference = new SharedPreference();

    public class LocalBinder extends Binder {
        public ServicePlayMusic getService() {
            return ServicePlayMusic.this;
        }
    }

    public ServicePlayMusic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null
                && intent.getParcelableArrayListExtra(Constant.EXTRA_TRACK_LIST_ITEM) != null) {
            if (!mTracks.isEmpty()) {
                mTracks.clear();
            }
            mTracks.addAll(
                    intent.<Track>getParcelableArrayListExtra(Constant.EXTRA_TRACK_LIST_ITEM));
            id = intent.getIntExtra(Constant.EXTRA_ID, 0);
            initNotification();
            stopMusic();
            if (mIsPause) {
                createMusic(id);
            } else {
                createMusic(id);
                playMusic();
            }
            setupHandle();
            doRegisterNotificationSign();
            mPreference.doPutBooleanMusic(this);
        }
        return START_STICKY;
    }

    private void doRegisterNotificationSign() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case Constant.ACTION_NOTIFICATION_PREVIOUS:
                        previousTrack();
                        break;
                    case Constant.ACTION_NOTIFICATION_PAUSE:
                        if (mMediaPlayer.isPlaying()) {
                            pauseMusic();
                        } else {
                            resumeMusic();
                        }
                        break;
                    case Constant.ACTION_NOTIFICATION_NEXT:
                        nextTrack();
                        break;
                    case Constant.ACTION_NOTIFICATION_CLOSE:
                        mPreference.doClearAllData(getApplicationContext());
                        stopForeground(true);
                        pauseMusic();
                        break;
                    default:
                        break;
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_NOTIFICATION_PREVIOUS);
        filter.addAction(Constant.ACTION_NOTIFICATION_PAUSE);
        filter.addAction(Constant.ACTION_NOTIFICATION_NEXT);
        filter.addAction(Constant.ACTION_NOTIFICATION_CLOSE);
        registerReceiver(mBroadcastReceiver, filter);
        startForeground(NOTIFICATION_ID, mNotification);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initNotification() {

        Context context = getApplicationContext();
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);

        //button previous
        Intent previous_intent = new Intent();
        previous_intent.setAction(Constant.ACTION_NOTIFICATION_PREVIOUS);
        PendingIntent pen_previous_intent = PendingIntent.getBroadcast(this, 0, previous_intent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.image_previous, pen_previous_intent);
        mRemoteViews.setImageViewResource(R.id.image_previous, R.drawable.ic_skip_previous);

        //button pause
        Intent pause_intent = new Intent();
        pause_intent.setAction(Constant.ACTION_NOTIFICATION_PAUSE);
        PendingIntent pen_pause_intent = PendingIntent.getBroadcast(this, 0, pause_intent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.image_pause, pen_pause_intent);
        mRemoteViews.setImageViewResource(R.id.image_pause, R.drawable.ic_pause);

        //button next
        Intent next_intent = new Intent();
        next_intent.setAction(Constant.ACTION_NOTIFICATION_NEXT);
        PendingIntent pen_next_intent = PendingIntent.getBroadcast(this, 0, next_intent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.image_next, pen_next_intent);
        mRemoteViews.setImageViewResource(R.id.image_next, R.drawable.ic_skip_next);

        //button close
        Intent close_intent = new Intent();
        close_intent.setAction(Constant.ACTION_NOTIFICATION_CLOSE);
        PendingIntent pen_close_intent = PendingIntent.getBroadcast(this, 0, close_intent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.image_close, pen_close_intent);
        mRemoteViews.setImageViewResource(R.id.image_close, R.drawable.ic_cancel);

        Intent notificationIntent = new Intent(context, SplashActivity.class);
        notificationIntent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_main);
        mRemoteViews.setImageViewBitmap(R.id.image, bitmap);
        CharSequence contentTitle = mTracks.get(id).getName();
        CharSequence contentText = mTracks.get(id).getNameArtist();
        setString(contentTitle, contentText);

        int icon = R.drawable.logo_main;
        mNotification = new Notification.Builder(this).build();
        mNotification.contentView = mRemoteViews;
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;
        mNotification.icon = icon;
        mNotification.when = System.currentTimeMillis();
        mNotification.contentIntent = pendingIntent;
    }

    public void cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID);
    }

    public void setNameOnNotification() {

        CharSequence contentTitle = mTracks.get(id).getName();
        CharSequence contentText = mTracks.get(id).getNameArtist();
        setString(contentTitle, contentText);
        startForeground(NOTIFICATION_ID, mNotification);
    }

    private void setString(CharSequence contentTitle, CharSequence contentText) {
        if (contentTitle.length() > MAX_LENGTH) {
            String subTitle = contentTitle.subSequence(MIN_LENGTH, MAX_LENGTH) + "...";
            mRemoteViews.setTextViewText(R.id.text_name_song, subTitle);
        } else {
            mRemoteViews.setTextViewText(R.id.text_name_song, contentTitle);
        }

        if (contentText.length() > MAX_LENGTH) {
            String subText = contentText.subSequence(MIN_LENGTH, MAX_LENGTH) + "...";
            mRemoteViews.setTextViewText(R.id.text_name_artist, subText);
        } else {
            mRemoteViews.setTextViewText(R.id.text_name_artist, contentText);
        }
    }

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
        }
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }
        cancelNotification();
        super.onDestroy();
    }

    private void setupHandle() {
        mHandler.removeCallbacks(sendCurrentTime);
        mHandler.postDelayed(sendCurrentTime, 1000);
    }

    private Runnable sendCurrentTime = new Runnable() {
        @Override
        public void run() {
            if (mMediaPlayer != null) {
                Intent intent = new Intent(Constant.ACTION_MEDIA_TIME);
                intent.putExtra(Constant.EXTRA_CURRENT_TIME, mMediaPlayer.getCurrentPosition());
                sendBroadcast(intent);
            }
            mHandler.postDelayed(sendCurrentTime, 1000);
        }
    };

    public void createMusic(int id) {
        try {
            String path = mTracks.get(id).getTrackData();
            Uri uri = Uri.parse(path);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.create(this, uri);
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: " + e.getMessage(), e);
        }
    }

    public Track getTrack() {
        return mTracks.get(id);
    }

    public void playMusic() {
        mMediaPlayer.start();
        mIsPause = false;
    }

    public void pauseMusic() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mIsPause = true;
            mRemoteViews.setImageViewResource(R.id.image_pause, R.drawable.ic_play);
            startForeground(NOTIFICATION_ID, mNotification);
        }
    }

    public void resumeMusic() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            mIsPause = false;
            mRemoteViews.setImageViewResource(R.id.image_pause, R.drawable.ic_pause);
            startForeground(NOTIFICATION_ID, mNotification);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void nextTrack() {
        stopMusic();
        //
        if (mIsRepeat) {
            if (mIsPause) {
                createMusic(id);
            } else {
                createMusic(id);
                playMusic();
            }
        } else if (mIsShuffle) {
            if (mIsPause) {
                Random rand = new Random();
                id = rand.nextInt((mTracks.size() - 1) + 1);
                createMusic(id);
            } else {
                Random rand = new Random();
                id = rand.nextInt((mTracks.size() - 1) + 1);
                createMusic(id);
                playMusic();
            }
        } else if (id < (mTracks.size() - 1)) {
            if (mIsPause) {
                createMusic(id + 1);
                id++;
            } else {
                createMusic(id + 1);
                playMusic();
                id++;
            }
        } else {
            if (mIsPause) {
                // play first song
                createMusic(0);
                id = 0;
            } else {
                // play first song
                createMusic(0);
                playMusic();
                id = 0;
            }
        }
        setNameOnNotification();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void previousTrack() {
        stopMusic();
        //-----------------
        if (mIsRepeat) {
            if (mIsPause) {
                createMusic(id);
            } else {
                createMusic(id);
                playMusic();
            }
        } else if (mIsShuffle) {
            if (mIsPause) {
                Random rand = new Random();
                id = rand.nextInt((mTracks.size() - 1) + 1);
                createMusic(id);
            } else {
                Random rand = new Random();
                id = rand.nextInt((mTracks.size() - 1) + 1);
                createMusic(id);
                playMusic();
            }
        } else if (id - 1 < 0) {
            if (mIsPause) {
                id = mTracks.size() - 1;
                createMusic(id);
            } else {
                id = mTracks.size() - 1;
                createMusic(id);
                playMusic();
            }
        } else {
            if (mIsPause) {
                // play first song
                createMusic(id - 1);
                id--;
            } else {
                // play first song
                createMusic(id - 1);
                playMusic();
                id--;
            }
        }
        setNameOnNotification();
    }

    public boolean isShuffle() {
        return mIsShuffle;
    }

    public void setShuffle(boolean shuffle) {
        mIsShuffle = shuffle;
    }

    public boolean isRepeat() {
        return mIsRepeat;
    }

    public void setRepeat(boolean repeat) {
        mIsRepeat = repeat;
    }

    public void stopMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    public void seekMusic() {
        if (mMediaPlayer != null && !mIsPause) {
            mMediaPlayer.seekTo(mediaLength);
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartService = new Intent(getApplicationContext(), this.getClass());
        restartService.setPackage(getPackageName());
        PendingIntent restartServicePI =
                PendingIntent.getService(getApplicationContext(), 1, restartService,
                        PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService =
                (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000,
                restartServicePI);
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