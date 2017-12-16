package com.fstyle.androidtrainning.screen.main;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.model.SearchAlbum;
import com.fstyle.androidtrainning.model.SearchTrack;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public interface MainContract {

    interface Viewer extends BaseView {

        void onListTrackSuccess(List<SearchTrack> track);

        void onListAlbumSuccess(List<SearchAlbum> album);

        void onListArtistSuccess(List<Artist> artist);

        void onListArtistFail();

        void onListAlbumFail();

        void onListTrackFail();

        void onFetchAllDataSuccess(boolean isFetchDataSuccess);
    }

    interface Presenter extends BasePresenter<Viewer> {

        void setApi(LastFmApi api);

        void doPassKeyWord(String newText);
    }
}
