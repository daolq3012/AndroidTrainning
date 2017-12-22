package com.fstyle.androidtrainning.screen.tabfavorite;

import com.fstyle.androidtrainning.data.local.entity.MovieEntity;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import java.util.ArrayList;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link TabFavoriteFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TabFavoritePresenter implements TabFavoriteContract.Presenter {
    private static final String TAG = TabFavoritePresenter.class.getName();

    TabFavoriteContract.FavoriteView mFavoriteView;
    private MoviesApi mMoviesApi;
    private List<MovieEntity> mFavoriteMovieEntityList = new ArrayList<>();
    private Movie mMovie = new Movie();

    TabFavoritePresenter() {
    }

    @Override
    public void setView(TabFavoriteContract.FavoriteView view) {
        mFavoriteView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void setMovieApi(MoviesApi moviesApi) {
        mMoviesApi = moviesApi;
    }
}
