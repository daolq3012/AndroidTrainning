package com.fstyle.androidtrainning.screen.main.playingfragments.lyrics;

import android.os.AsyncTask;
import com.fstyle.androidtrainning.data.local.storage.LyricSdCard;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by ChuongPC on 26/12/2017.
 */

public class LoadLyricAsyncTask extends AsyncTask<File, Void, ArrayList<File>> {

    private OnLoadLyricListener mOnLoadLyricListener;

    public void setOnLoadLyricListener(OnLoadLyricListener onLoadLyricListener) {
        mOnLoadLyricListener = onLoadLyricListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<File> doInBackground(File... files) {
        return LyricSdCard.findLyrics(files[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<File> files) {
        super.onPostExecute(files);
        mOnLoadLyricListener.onLoadLyricSuccess(files);
    }
}
