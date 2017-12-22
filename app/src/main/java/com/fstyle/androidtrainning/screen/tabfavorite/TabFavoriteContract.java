package com.fstyle.androidtrainning.screen.tabfavorite;

import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TabFavoriteContract {
    /**
     * View.
     */
    interface FavoriteView extends BaseView {

    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<FavoriteView> {
        void setMovieApi(MoviesApi moviesApi);
    }
}
