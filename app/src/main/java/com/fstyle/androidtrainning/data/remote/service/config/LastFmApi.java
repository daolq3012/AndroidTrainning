package com.fstyle.androidtrainning.data.remote.service.config;

import com.fstyle.androidtrainning.data.remote.service.response.AlbumSearchResponse;
import com.fstyle.androidtrainning.data.remote.service.response.ArtistSearchResponse;
import com.fstyle.androidtrainning.data.remote.service.response.RecentTrackResponse;
import com.fstyle.androidtrainning.data.remote.service.response.TopAlbumResponse;
import com.fstyle.androidtrainning.data.remote.service.response.TopTrackResponse;
import com.fstyle.androidtrainning.data.remote.service.response.TrackSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 12/08/17.
 */

public interface LastFmApi {

    @GET("/2.0")
    Call<TopAlbumResponse> getTopAlbum(@Query("method") String method,
            @Query("user") String nameUser, @Query("api_key") String apiKey,
            @Query("format") String format);

    @GET("/2.0")
    Call<RecentTrackResponse> getRecentTrack(@Query("method") String method,
            @Query("user") String nameUser, @Query("api_key") String apiKey,
            @Query("format") String format);

    @GET("/2.0")
    Call<TopTrackResponse> getTopTrack(@Query("method") String method,
            @Query("user") String nameUser, @Query("api_key") String apiKey,
            @Query("format") String format);

    @GET("/2.0")
    Call<TrackSearchResponse> getTrackSearch(@Query("method") String method,
            @Query("track") String keyWord, @Query("api_key") String apiKey,
            @Query("format") String format);

    @GET("/2.0")
    Call<AlbumSearchResponse> getAlbumSearch(@Query("method") String method,
            @Query("album") String keyWord, @Query("api_key") String apiKey,
            @Query("format") String format);

    @GET("/2.0")
    Call<ArtistSearchResponse> getArtistSearch(@Query("method") String method,
            @Query("artist") String keyWord, @Query("api_key") String apiKey,
            @Query("format") String format);
}
