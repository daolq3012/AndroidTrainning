package com.fstyle.androidtrainning.screen.searchonline;

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
 * Listens to user actions from the UI ({@link SearchOnlineFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SearchOnlinePresenter implements SearchOnlineContract.Presenter {
    private static final String TAG = SearchOnlinePresenter.class.getName();

    private SearchOnlineContract.Viewer mView;
    private boolean isFetchDataSuccess = true;
    private LastFmApi mApi;

    @Override
    public void setView(SearchOnlineContract.Viewer view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void doPassKeyWord() {
        String newText = mView.getEditText();
        doSearchTrack(newText);
        doSearchAlbum(newText);
        doSearchArtist(newText);
        mView.onFetchAllDataSuccess(isFetchDataSuccess);
    }

    @Override
    public void setApi(LastFmApi api) {
        mApi = api;
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
                    mView.onGetListArtistFail();
                    isFetchDataSuccess = false;
                    return;
                }
                isFetchDataSuccess = true;
                mView.onGetListArtistSuccess(
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
                    mView.onGetListAlbumFail();
                    isFetchDataSuccess = false;
                    return;
                }
                isFetchDataSuccess = true;
                mView.onGetListAlbumSuccess(response.body().getResults().getAlbummatches().getAlbum());
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
                    mView.onGetListTrackFail();
                    isFetchDataSuccess = false;
                    return;
                }
                isFetchDataSuccess = true;
                mView.onGetListTrackSuccess(response.body().getResults().getTrackmatches().getTrack());
            }

            @Override
            public void onFailure(@NonNull Call<TrackSearchResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
