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
    public static final String STR_PATH_LOWER_4 =
            Environment.getExternalStorageDirectory().getPath() + "";
    // public static final String STR_PATH_UPPER_4 = Environment.getExternalStorageDirectory()
    // .getPath();
    // path sd card cho android 4.4.x tro len
    public static final String STR_PATH_UPPER_4 = System.getenv("SECONDARY_STORAGE");
    public static final String EXTRA_NAME_ALBUM = "EXTRA_NAME_ALBUM";
    public static final String EXTRA_NAME_ARTIST = "EXTRA_NAME_ARTIST";
}
