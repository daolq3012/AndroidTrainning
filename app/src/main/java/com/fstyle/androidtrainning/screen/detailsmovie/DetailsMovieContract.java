package com.fstyle.androidtrainning.screen.detailsmovie;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DetailsMovieContract {
    /**
     * View.
     */
    interface DetailsMovieView extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<DetailsMovieView> {
        void setView(DetailsMovieContract.DetailsMovieView detailsMovieView);
    }
}
