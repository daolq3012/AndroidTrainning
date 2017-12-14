package com.fstyle.androidtrainning.screen.tabhome;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TabHomeContract {
    /**
     * View.
     */
    interface HomeView extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
