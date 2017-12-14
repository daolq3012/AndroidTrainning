package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.toptrack;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public interface TopTrackContract {

    interface Viewer extends BaseView {
        void showToastFail(String message);

        void onListTrackSuccess(List<Track> tracks);
    }

    interface Presenter extends BasePresenter<Viewer> {

        void setApi(LastFmApi api);

        void getTopTrack();
    }
}
