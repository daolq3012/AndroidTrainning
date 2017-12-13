package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.toptrack;

import android.support.annotation.NonNull;
import android.util.Log;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.data.remote.service.response.TopTrackResponse;
import com.fstyle.androidtrainning.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 12/10/17.
 */

public class TopTrackPresenter implements TopTrackContract.Presenter {

    private TopTrackContract.Viewer mViewer;
    private LastFmApi mApi;
    private static final String TAG = "TopTrackPresenter";

    @Override
    public void setViewer(TopTrackContract.Viewer viewer) {
        mViewer = viewer;
    }

    @Override
    public void setApi(LastFmApi api) {
        mApi = api;
    }

    //TODO chua hieu user lay tu dau @@
    @Override
    public void getTopTrack() {
        mApi.getTopTrack(Constant.METHOD_GET_TOP_TRACK, Constant.USER_LAST_FM, Constant.API_KEY,
                Constant.FORMAT_TYPE_API).enqueue(new Callback<TopTrackResponse>() {
            @Override
            public void onResponse(@NonNull Call<TopTrackResponse> call,
                    @NonNull Response<TopTrackResponse> response) {
                if (response.body().getTopTracks() != null) {
                    mViewer.onGetTopTracksSuccess(response.body().getTopTracks());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopTrackResponse> call, @NonNull Throwable t) {
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
