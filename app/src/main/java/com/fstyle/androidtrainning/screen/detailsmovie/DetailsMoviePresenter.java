package com.fstyle.androidtrainning.screen.detailsmovie;

import android.util.Log;
import com.fstyle.androidtrainning.data.model.Genre;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.utils.Constant;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Listens to user actions from the UI, retrieves the data and updates
 * the UI as required.
 */
final class DetailsMoviePresenter implements DetailsMovieContract.Presenter {
    private static final String TAG = DetailsMoviePresenter.class.getName();

    private DetailsMovieContract.DetailsMovieView mDetailsMovieView;
    private MoviesApi mMoviesApi;

    DetailsMoviePresenter() {
    }

    @Override
    public void setView(DetailsMovieContract.DetailsMovieView detailsMovieView) {
        mDetailsMovieView = detailsMovieView;
    }

    @Override
    public void setMovieApi(MoviesApi moviesApi) {
        mMoviesApi = moviesApi;
    }

    @Override
    public void getDetailsMovie() {
        long movieId = mDetailsMovieView.getMovieId();
        mMoviesApi.getDetailsMovie(movieId, Constant.API_KEY, Constant.LANGUAGE)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.body() == null) {
                            return;
                        }
                        List<String> listGenre = new ArrayList<>();
                        for (Genre genre : response.body().getGenres()) {
                            listGenre.add(genre.getName());
                        }
                        mDetailsMovieView.onDetailsMovieSuccess(response.body(), listGenre);
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
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
