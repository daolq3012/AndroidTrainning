package com.fstyle.androidtrainning.utils;

import android.os.Environment;

/**
 * Created by Administrator on 12/08/17.
 */

public final class Constant {

    public static final String FORMAT_TYPE_API = "json";
    public static final String API_KEY = "c293e5365cf096aa6d928050cde0c190";
    public static final String METHOD_GET_TOP_ALBUM = "user.gettopalbums";
    public static final String METHOD_GET_RECENT_TRACK = "user.getrecenttracks";
    public static final String METHOD_GET_TOP_TRACK = "user.gettoptracks";
    public static final String METHOD_SEARCH_TRACK = "track.search";
    public static final String METHOD_SEARCH_ALBUM = "album.search";
    public static final String METHOD_SEARCH_ARTIST = "artist.search";
    public static final String URL = "http://ws.audioscrobbler.com/";
    public static final String USER_LAST_FM = "rj";
    public static final String TYPE_LYRIC = ".lrc";
    // path sd card cho android 4.4.x tro xuong
    public static final String STR_PATH_UPPER_4 =
            Environment.getExternalStorageDirectory().getPath();
    // public static final String STR_PATH_UPPER_4 = Environment.getExternalStorageDirectory()
    // .getPath();
    // path sd card cho android 4.4.x tro len
    public static final String STR_PATH_LOWER_4 = System.getenv("SECONDARY_STORAGE");
    public static final String EXTRA_NAME_ALBUM = "EXTRA_NAME_ALBUM";
    public static final String EXTRA_NAME_ARTIST = "EXTRA_NAME_ARTIST";
    public static final String ACTION_ITEM_PLAY = "com.fstyle.androidtrainning.action.item.play";
    public static final String EXTRA_TRACK_ITEM = "EXTRA_TRACK_ITEM";
    public static final String EXTRA_CURRENT_TIME = "EXTRA_CURRENT_TIME";
    public static final String ACTION_MEDIA_TIME = "ACTION_MEDIA_TIME";
    public static final int RESULT_CODE = 101;
    public static final int REQUEST_CODE = 100;
    public static final String EXTRA_TRACK_LIST_ITEM = "EXTRA_TRACK_LIST_ITEM";
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String TYPE_MP3 = ".mp3";
    public static final int RESULT_CODE_ALBUM = 102;
    public static final int RESULT_CODE_ARTIST = 103;
    public static final String ACTION_NOTIFICATION_PREVIOUS =
            "com.fstyle.androidtrainning.action.notification.previous";
    public static final String ACTION_NOTIFICATION_PAUSE =
            "com.fstyle.androidtrainning.action.notification.pause";
    public static final String ACTION_NOTIFICATION_NEXT =
            "com.fstyle.androidtrainning.action.notification.next";
    public static final String ACTION_NOTIFICATION_CLOSE =
            "com.fstyle.androidtrainning.action.notification.close";
    public static final String SHAREDPREF_IS_MUSIC_ENABLE = "SHAREDPREF_IS_MUSIC_ENABLE";
    public static final String IS_MUSIC_ENABLE = "IS_MUSIC_ENABLE";
    public static final String SHAREDPREF_DATA_MUSIC = "SHAREDPREF_DATA_MUSIC";
    public static final String SHAREDPREF_CHECK_MUSIC = "SHAREDPREF_CHECK_MUSIC";
    public static final String SHAREDPREF_NAME_SONG = "SHAREDPREF_NAME_SONG";
    public static final String SHAREDPREF_NAME_ARTIST = "SHAREDPREF_NAME_ARTIST";
    public static final String SHAREDPREF_TIME_START = "SHAREDPREF_TIME_START";
    public static final String SHAREDPREF_TIME_TOTAL = "SHAREDPREF_TIME_TOTAL";
    public static final String SHAREDPREF_SEEKBAR_POS = "SHAREDPREF_SEEKBAR_POS";
    public static final String SHAREDPREF_SETTING = "SHAREDPREF_SETTING";
    public static final String SHAREDPREF_IS_REPEAT = "SHAREDPREF_IS_REPEAT";
    public static final String SHAREDPREF_IS_SHUFFLE = "SHAREDPREF_IS_SHUFFLE";
}
