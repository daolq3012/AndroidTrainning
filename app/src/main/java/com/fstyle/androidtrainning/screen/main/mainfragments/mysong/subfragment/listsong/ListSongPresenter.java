package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.content.Context;

import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.model.Track;

import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public class ListSongPresenter implements ListSongContract.Presenter {

    private ListSongContract.Viewer mViewer;
    private ExternalData external = new ExternalData();

    @Override
    public void setView(ListSongContract.Viewer view) {
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
        external.scanAllMusic(context);
        List<Track> tracks = external.getArrayListTrack();
        mViewer.onGetListTrackSuccess(tracks);
    }
}
