package com.fstyle.androidtrainning.screen.moremovies;

import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MoreMoviesContract {
    /**
     * View.
     */
    interface MoreMoviesView extends BaseView {
        String getCategoryMovie();

        void onListMoreMoviesSuccess(List<Movie> results);

        void onListMovieSuccess(List<Movie> results);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<MoreMoviesView> {
        void setMoviesApi(MoviesApi moviesApi);

        void getListMoreMovie(int currentPage);

        void getListMoviesByCategory();
    }
}
