package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.artist;

import android.content.Context;

import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.model.Artist;

import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public class ArtistPresenter implements ArtistContract.Presenter {

    private ArtistContract.Viewer mViewer;
    private ExternalData external = new ExternalData();

    @Override
    public void setView(ArtistContract.Viewer view) {
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
        external.scanAllArtist(context);
        List<Artist> artists = external.getArrayListArtist();
        mViewer.onGetListArtistSuccess(artists);
    }
}
