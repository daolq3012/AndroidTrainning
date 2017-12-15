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
    interface ViewDetail extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void setView(DetailsMovieContract.ViewDetail viewDetail);
    }
}
