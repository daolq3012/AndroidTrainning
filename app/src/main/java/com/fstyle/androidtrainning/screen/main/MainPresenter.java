package com.fstyle.androidtrainning.screen.main;

import android.support.annotation.NonNull;
import android.util.Log;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.data.remote.service.response.AlbumSearchResponse;
import com.fstyle.androidtrainning.data.remote.service.response.ArtistSearchResponse;
import com.fstyle.androidtrainning.data.remote.service.response.TrackSearchResponse;
import com.fstyle.androidtrainning.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 12/10/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.Viewer mViewer;
    private LastFmApi mApi;
    private static final String TAG = "MainPresenter";
    private static final int SEARCH_AMOUNT = 4;
    private static final int MIN = 0;
    private boolean isFetchDataSuccess = true;

    @Override
    public void setView(MainContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setApi(LastFmApi api) {
        mApi = api;
    }

    @Override
    public void doPassKeyWord(String newText) {
        doSearchTrack(newText);
        doSearchAlbum(newText);
        doSearchArtist(newText);
        mViewer.onFetchAllDataSuccess(isFetchDataSuccess);
    }

    private void doSearchArtist(String newText) {
        mApi.getArtistSearch(Constant.METHOD_SEARCH_ARTIST, newText, Constant.API_KEY,
                Constant.FORMAT_TYPE_API).enqueue(new Callback<ArtistSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArtistSearchResponse> call,
                    @NonNull Response<ArtistSearchResponse> response) {
                if (response.body() == null
                        || response.body().getResults().getArtistmatches().getArtist() == null) {
                    isFetchDataSuccess = false;
                    return;
                }
                if (response.body().getResults().getArtistmatches().getArtist().isEmpty()) {
                    mViewer.onListArtistFail();
                    isFetchDataSuccess = false;
                    return;
                }
                isFetchDataSuccess = true;
                mViewer.onListArtistSuccess(
                        response.body().getResults().getArtistmatches().getArtist());
            }

            @Override
            public void onFailure(@NonNull Call<ArtistSearchResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void doSearchAlbum(String newText) {
        mApi.getAlbumSearch(Constant.METHOD_SEARCH_ALBUM, newText, Constant.API_KEY,
                Constant.FORMAT_TYPE_API).enqueue(new Callback<AlbumSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<AlbumSearchResponse> call,
                    @NonNull Response<AlbumSearchResponse> response) {
                if (response.body() == null
                        || response.body().getResults().getAlbummatches().getAlbum() == null) {
                    isFetchDataSuccess = false;
                    return;
                }
                if (response.body().getResults().getAlbummatches().getAlbum().isEmpty()) {
                    mViewer.onListAlbumFail();
                    isFetchDataSuccess = false;
                    return;
                }
                isFetchDataSuccess = true;
                mViewer.onListAlbumSuccess(
                        response.body().getResults().getAlbummatches().getAlbum());
            }

            @Override
            public void onFailure(@NonNull Call<AlbumSearchResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void doSearchTrack(String newText) {
        mApi.getTrackSearch(Constant.METHOD_SEARCH_TRACK, newText, Constant.API_KEY,
                Constant.FORMAT_TYPE_API).enqueue(new Callback<TrackSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackSearchResponse> call,
                    @NonNull Response<TrackSearchResponse> response) {
                if (response.body() == null
                        || response.body().getResults().getTrackmatches().getTrack() == null) {
                    isFetchDataSuccess = false;
                    return;
                }
                if (response.body().getResults().getTrackmatches().getTrack().isEmpty()) {
                    mViewer.onListTrackFail();
                    isFetchDataSuccess = false;
                    return;
                }
                isFetchDataSuccess = true;
                mViewer.onListTrackSuccess(
                        response.body().getResults().getTrackmatches().getTrack());
            }

            @Override
            public void onFailure(@NonNull Call<TrackSearchResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
