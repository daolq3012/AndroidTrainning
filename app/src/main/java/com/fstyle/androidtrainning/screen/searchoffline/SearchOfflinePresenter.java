package com.fstyle.androidtrainning.screen.searchoffline;

import android.content.Context;
import com.fstyle.androidtrainning.data.local.storage.ExternalData;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.model.Track;
import java.util.ArrayList;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link SearchOfflineFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SearchOfflinePresenter implements SearchOfflineContract.Presenter {
    private static final String TAG = SearchOfflinePresenter.class.getName();

    private SearchOfflineContract.Viewer mView;
    private ExternalData external = new ExternalData();
    private int countFail = 0;

    @Override
    public void setView(SearchOfflineContract.Viewer view) {
        mView = view;
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
        external.scanAllAlbum(context);
        external.scanAllArtist(context);
    }

    @Override
    public void doPassKeyWord() {
        String newText = mView.getEditText();
        doSearchTrack(newText);
        doSearchAlbum(newText);
        doSearchArtist(newText);
        mView.onFetchAllDataSuccess(countFail);
    }

    private void doSearchArtist(String newText) {
        List<Artist> artists = new ArrayList<>();
        if (external.getArrayListArtist() == null) {
            countFail++;
            return;
        }
        for (Artist artist : external.getArrayListArtist()) {
            if (artist.getName().contains(newText)) {
                artists.add(artist);
            }
        }
        if (artists.isEmpty()) {
            mView.onGetListArtistFail();
            countFail++;
            return;
        }
        countFail = 0;
        mView.onGetListArtistSuccess(artists);
    }

    private void doSearchTrack(String newText) {
        List<Track> tracks = new ArrayList<>();
        if (external.getArrayListTrack() == null) {
            countFail++;
            return;
        }
        for (Track track : external.getArrayListTrack()) {
            if (track.getName().contains(newText)) {
                tracks.add(track);
            }
        }
        if (tracks.isEmpty()) {
            mView.onGetListTrackFail();
            countFail++;
            return;
        }
        countFail = 0;
        mView.onGetListTrackSuccess(tracks);
    }

    private void doSearchAlbum(String newText) {
        List<Album> albums = new ArrayList<>();
        if (external.getArrayListAlbum() == null) {
            countFail++;
            return;
        }
        for (Album album : external.getArrayListAlbum()) {
            if (album.getName().contains(newText)) {
                albums.add(album);
            }
        }
        if (albums.isEmpty()) {
            mView.onGetListAlbumFail();
            countFail++;
            return;
        }
        countFail = 0;
        mView.onGetListAlbumSuccess(albums);
    }
}
