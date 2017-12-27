package com.fstyle.androidtrainning.data.local.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import com.fstyle.androidtrainning.utils.Constant;

/**
 * Created by ChuongPC on 26/12/2017.
 */

public class SharedPreference {

    private String mNameSong, mNameArtist, mTextStart, mTextTotal;
    private int mPosition;
    private boolean mIsRepeat, mIsShuffle;

    public SharedPreference() {
    }

    public String getNameSong() {
        return mNameSong;
    }

    public String getNameArtist() {
        return mNameArtist;
    }

    public String getTextStart() {
        return mTextStart;
    }

    public String getTextTotal() {
        return mTextTotal;
    }

    public int getPosition() {
        return mPosition;
    }

    public boolean isRepeat() {
        return mIsRepeat;
    }

    public boolean isShuffle() {
        return mIsShuffle;
    }

    public void doPutBooleanMusic(Context context) {

        SharedPreferences preference =
                context.getSharedPreferences(Constant.SHAREDPREF_CHECK_MUSIC, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(Constant.SHAREDPREF_IS_MUSIC_ENABLE, true);
        editor.apply();
    }

    public boolean doGetBooleanMusic(Context context) {

        SharedPreferences preference =
                context.getSharedPreferences(Constant.SHAREDPREF_CHECK_MUSIC, Context.MODE_PRIVATE);
        boolean isMusicEnable = preference.getBoolean(Constant.SHAREDPREF_IS_MUSIC_ENABLE, false);
        return isMusicEnable;
    }

    public void doPutDataMusic(Context context, String nameSong, String nameArtist,
            String timeStart, String timeTotal, int position) {

        SharedPreferences preference =
                context.getSharedPreferences(Constant.SHAREDPREF_DATA_MUSIC, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(Constant.SHAREDPREF_NAME_SONG, nameSong);
        editor.putString(Constant.SHAREDPREF_NAME_ARTIST, nameArtist);
        editor.putString(Constant.SHAREDPREF_TIME_START, timeStart);
        editor.putString(Constant.SHAREDPREF_TIME_TOTAL, timeTotal);
        editor.putInt(Constant.SHAREDPREF_SEEKBAR_POS, position);
        editor.apply();
    }

    public void doGetDataMusic(Context context) {

        SharedPreferences preference =
                context.getSharedPreferences(Constant.SHAREDPREF_DATA_MUSIC, Context.MODE_PRIVATE);
        mNameSong = preference.getString(Constant.SHAREDPREF_NAME_SONG, "");
        mNameArtist = preference.getString(Constant.SHAREDPREF_NAME_ARTIST, "");
        mTextStart = preference.getString(Constant.SHAREDPREF_TIME_START, "");
        mTextTotal = preference.getString(Constant.SHAREDPREF_TIME_TOTAL, "");
        mPosition = preference.getInt(Constant.SHAREDPREF_SEEKBAR_POS, 0);
    }

    public void doClearAllData(Context context) {
        context.getSharedPreferences(Constant.SHAREDPREF_DATA_MUSIC, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
        context.getSharedPreferences(Constant.SHAREDPREF_CHECK_MUSIC, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
        context.getSharedPreferences(Constant.SHAREDPREF_SETTING, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

    public void doPutSetting(Context context, boolean isRepeat, boolean isShuffle) {

        SharedPreferences preference =
                context.getSharedPreferences(Constant.SHAREDPREF_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(Constant.SHAREDPREF_IS_REPEAT, isRepeat);
        editor.putBoolean(Constant.SHAREDPREF_IS_SHUFFLE, isShuffle);
        editor.apply();
    }

    public void doGetSetting(Context context) {

        SharedPreferences preference =
                context.getSharedPreferences(Constant.SHAREDPREF_SETTING, Context.MODE_PRIVATE);
        mIsRepeat = preference.getBoolean(Constant.SHAREDPREF_IS_REPEAT, false);
        mIsShuffle = preference.getBoolean(Constant.SHAREDPREF_IS_SHUFFLE, false);
    }
}
