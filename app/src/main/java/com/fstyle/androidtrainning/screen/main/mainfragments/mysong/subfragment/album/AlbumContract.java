package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album;

import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public interface AlbumContract {

    interface Viewer extends BaseView {
        void onGetListAlbumSuccess(List<Album> albums);
    }

    interface Presenter extends BasePresenter<Viewer> {
        void getDataExternal();
    }
}
