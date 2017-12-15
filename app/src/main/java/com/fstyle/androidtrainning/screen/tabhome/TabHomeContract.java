package com.fstyle.androidtrainning.screen.tabhome;

import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TabHomeContract {
    /**
     * View.
     */
    interface HomeView extends BaseView {
        void onListNowPlayingMovieSuccess(List<Movie> listNowPlayingMovie);

        void onListUpComingMovieSuccess(List<Movie> listUpComingMovie);

        void onListTopRatedMovieSuccess(List<Movie> listTopRatedMovie);

        void onListPopularMoviesSuccess(List<Movie> listPopularMovie);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<HomeView> {
        void setMoviesApi(MoviesApi moviesApi);

        void getListNowPlayingMovie();

        void getListUpComingMovie();

        void getListTopRatedMovie();

        void getListPopularMovie();
    }
}
