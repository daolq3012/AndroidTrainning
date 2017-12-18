package com.fstyle.androidtrainning.screen.tabsearch;

import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TabSearchContract {
    /**
     * View.
     */
    interface SearchView extends BaseView {

        void onListSearchMovieSuccess(List<Movie> listSearchMovie);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<SearchView> {
        void setMoviesApi(MoviesApi moviesApi);

        void getListSearchMovie(String keyWord);
    }
}
