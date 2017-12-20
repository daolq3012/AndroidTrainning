package com.fstyle.androidtrainning.screen.searchonline;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.model.SearchAlbum;
import com.fstyle.androidtrainning.model.SearchTrack;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchOnlineContract {
    /**
     * View.
     */
    interface Viewer extends BaseView {
        void onGetListTrackSuccess(List<SearchTrack> track);

        void onGetListAlbumSuccess(List<SearchAlbum> album);

        void onGetListArtistSuccess(List<Artist> artist);

        void onGetListArtistFail();

        void onGetListAlbumFail();

        void onGetListTrackFail();

        void onFetchAllDataSuccess(boolean isFetchDataSuccess);

        String getEditText();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<Viewer> {
        void doPassKeyWord();

        void setApi(LastFmApi api);
    }
}
