package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.artist;

import android.content.Context;

import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public interface ArtistContract {

    interface Viewer extends BaseView {
        void onGetListArtistSuccess(List<Artist> artists);
    }

    interface Presenter extends BasePresenter<Viewer> {
        void getDataExternal(Context context);
    }
}
