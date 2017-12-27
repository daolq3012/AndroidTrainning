package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album;

import android.content.Context;
import com.fstyle.androidtrainning.model.Album;
import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public class AlbumPresenter implements AlbumContract.Presenter, OnLoadAlbumListener {

    private AlbumContract.Viewer mViewer;

    private Context mContext;
    private LoadAlbumAsyncTask mLoadAlbumAsyncTask = new LoadAlbumAsyncTask();

    AlbumPresenter(Context context) {
        mContext = context;
        mLoadAlbumAsyncTask.execute(context);
        mLoadAlbumAsyncTask.setOnLoadAlbumListener(this);
    }

    @Override
    public void setView(AlbumContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onLoadAlbumSuccess(List<Album> albums) {
        mViewer.onGetListAlbumSuccess(albums);
    }
}
