package com.fstyle.androidtrainning.data.service.config;

import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.response.GetListMoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 12/08/17.
 */

public interface MoviesApi {
    @GET("/3/movie/{category}")
    Call<GetListMoviesResponse> getListMovies(@Path("category") String category,
            @Query("api_key") String apiKey, @Query("language") String language,
            @Query("page") int page);

    @GET("/3/movie/{movieid}")
    Call<Movie> getDetailsMovie(@Path("movieid") long movieId, @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("3/search/movie")
    Call<GetListMoviesResponse> searchMovies(@Query("query") String keyWord,
            @Query("api_key") String apiKey, @Query("language") String language,
            @Query("page") int page);
}
