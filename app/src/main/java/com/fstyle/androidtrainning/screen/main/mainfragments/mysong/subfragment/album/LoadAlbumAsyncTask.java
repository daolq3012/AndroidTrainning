package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album;

import android.content.Context;
import android.os.AsyncTask;
import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.model.Album;
import java.util.List;

/**
 * Created by ChuongPC on 27/12/2017.
 */

public class LoadAlbumAsyncTask extends AsyncTask<Context, Void, List<Album>> {

    private ExternalData external = new ExternalData();
    private OnLoadAlbumListener mOnLoadAlbumListener;

    public void setOnLoadAlbumListener(OnLoadAlbumListener onLoadAlbumListener) {
        mOnLoadAlbumListener = onLoadAlbumListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Album> doInBackground(Context... contexts) {
        external.scanAllAlbum(contexts[0]);
        return external.getArrayListAlbum();
    }

    @Override
    protected void onPostExecute(List<Album> albums) {
        super.onPostExecute(albums);
        mOnLoadAlbumListener.onLoadAlbumSuccess(albums);
    }
}
