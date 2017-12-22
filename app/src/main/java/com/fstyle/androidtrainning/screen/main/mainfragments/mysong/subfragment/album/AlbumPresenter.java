package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album;

import android.content.Context;

import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.model.Album;

import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public class AlbumPresenter implements AlbumContract.Presenter {

    private AlbumContract.Viewer mViewer;
    private ExternalData external = new ExternalData();

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
    public void getDataExternal(Context context) {
        external.scanAllAlbum(context);
        List<Album> albums = external.getArrayListAlbum();
        mViewer.onGetListAlbumSuccess(albums);
    }
}
