package com.fstyle.androidtrainning.data.service.config;

import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.response.GetListNowPlayingResponse;
import com.fstyle.androidtrainning.data.service.response.GetListPopularResponse;
import com.fstyle.androidtrainning.data.service.response.GetListTopRatedResponse;
import com.fstyle.androidtrainning.data.service.response.GetListUpcomingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 12/08/17.
 */

public interface MoviesApi {
    @GET("/3/movie/upcoming")
    Call<GetListUpcomingResponse> getListUpcomingMovies(@Query("api_key") String apiKey,
            @Query("language") String language, @Query("page") int page);

    @GET("/3/movie/popular")
    Call<GetListPopularResponse> getListPopularMovies(@Query("api_key") String apiKey,
            @Query("language") String language, @Query("page") int page);

    @GET("/3/movie/top_rated")
    Call<GetListTopRatedResponse> getListTopRatedMovies(@Query("api_key") String apiKey,
            @Query("language") String language, @Query("page") int page);

    @GET("/3/movie/now_playing")
    Call<GetListNowPlayingResponse> getListNowPlayingMovies(@Query("api_key") String apiKey,
            @Query("language") String language, @Query("page") int page);

    @GET("/3/movie/{movieid}")
    Call<Movie> getDetailsMovie(@Path("movieid") long movie_id, @Query("api_key") String apiKey,
            @Query("language") String language);
}
