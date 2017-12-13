package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.topalbum;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.TopAlbums;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * Created by Administrator on 12/10/17.
 */

public interface TopAlbumContract {

    interface Viewer extends BaseView {
        void showToastFail(String message);

        void onGetTopAlbumsSuccess(TopAlbums albums);
    }

    interface Presenter extends BasePresenter {

        void setViewer(Viewer viewer);

        void setApi(LastFmApi api);

        void getTopAlbum();
    }
}
