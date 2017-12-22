package com.fstyle.androidtrainning.screen.searchoffline;

import android.content.Context;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchOfflineContract {
    /**
     * View.
     */
    interface Viewer extends BaseView {

        void onGetListTrackSuccess(List<Track> track);

        void onGetListAlbumSuccess(List<Album> album);

        void onGetListArtistSuccess(List<Artist> artist);

        void onGetListArtistFail();

        void onGetListAlbumFail();

        void onGetListTrackFail();

        void onFetchAllDataSuccess(int countFail);

        String getEditText();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<Viewer> {

        void getDataExternal(Context context);

        void doPassKeyWord();
    }
}
