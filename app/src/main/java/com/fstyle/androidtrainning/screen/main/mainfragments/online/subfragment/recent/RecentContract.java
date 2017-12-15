package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.recent;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.model.RecentTrack;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * Created by Administrator on 12/10/17.
 */

public interface RecentContract {

    interface Viewer extends BaseView {

        void showToastFail(String message);

        void onListRecentTrackSuccess(List<RecentTrack> tracks);
    }

    interface Presenter extends BasePresenter<Viewer> {

        void setApi(LastFmApi api);

        void getRecentTrack();
    }
}
