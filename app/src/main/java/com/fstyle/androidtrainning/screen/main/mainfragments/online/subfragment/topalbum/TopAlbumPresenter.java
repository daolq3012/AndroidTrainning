package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.topalbum;

import android.support.annotation.NonNull;
import android.util.Log;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.data.remote.service.response.TopAlbumResponse;
import com.fstyle.androidtrainning.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 12/10/17.
 */

public class TopAlbumPresenter implements TopAlbumContract.Presenter {

    private TopAlbumContract.Viewer mViewer;
    private LastFmApi mApi;
    private static final String TAG = "TopAlbumPresenter";

    @Override
    public void setApi(LastFmApi api) {
        mApi = api;
    }

    @Override
    public void getTopAlbum() {
        mApi.getTopAlbum(Constant.METHOD_GET_TOP_ALBUM, Constant.USER_LAST_FM, Constant.API_KEY,
                Constant.FORMAT_TYPE_API).enqueue(new Callback<TopAlbumResponse>() {
            @Override
            public void onResponse(@NonNull Call<TopAlbumResponse> call,
                    @NonNull Response<TopAlbumResponse> response) {
                if (response.body().getTopAlbums() != null
                        && response.body().getTopAlbums().getAlbum() != null) {
                    mViewer.onListAlbumSuccess(response.body().getTopAlbums().getAlbum());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopAlbumResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void setView(TopAlbumContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
