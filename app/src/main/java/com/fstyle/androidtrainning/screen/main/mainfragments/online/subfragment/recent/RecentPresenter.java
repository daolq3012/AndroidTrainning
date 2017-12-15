package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.recent;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.data.remote.service.response.RecentTrackResponse;
import com.fstyle.androidtrainning.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 12/10/17.
 */

public class RecentPresenter implements RecentContract.Presenter {

    private RecentContract.Viewer mViewer;
    private LastFmApi mApi;

    private static final String TAG = "RecentPresenter";

    @Override
    public void setViewer(RecentContract.Viewer viewer) {
        mViewer = viewer;
    }

    @Override
    public void setApi(LastFmApi api) {
        mApi = api;
    }

    @Override
    public void getRecentTrack() {
        mApi.getRecentTrack(Constant.METHOD_GET_RECENT_TRACK, Constant.USER_LAST_FM,
                Constant.API_KEY, Constant.FORMAT_TYPE_API)
                .enqueue(new Callback<RecentTrackResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<RecentTrackResponse> call,
                                           @NonNull Response<RecentTrackResponse> response) {
                        if (response.body().getRecentTracks() != null) {
                            mViewer.onGetRecentTracksSuccess(response.body().getRecentTracks());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RecentTrackResponse> call,
                                          @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
