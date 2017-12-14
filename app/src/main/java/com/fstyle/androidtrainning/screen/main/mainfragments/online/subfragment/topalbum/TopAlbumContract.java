package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.topalbum;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public interface TopAlbumContract {

    interface Viewer extends BaseView {
        void showToastFail(String message);

        void onListAlbumSuccess(List<Album> albums);
    }

    interface Presenter extends BasePresenter<Viewer> {

        void setApi(LastFmApi api);

        void getTopAlbum();
    }
}
