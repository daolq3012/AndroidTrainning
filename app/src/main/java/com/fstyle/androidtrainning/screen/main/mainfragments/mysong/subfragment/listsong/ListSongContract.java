package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.content.Context;

import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public interface ListSongContract {

    interface Viewer extends BaseView {

        void onGetListTrackSuccess(List<Track> tracks);
    }

    interface Presenter extends BasePresenter<Viewer> {

        void getDataExternal(Context context);
    }
}
