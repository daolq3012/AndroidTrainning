package com.fstyle.androidtrainning.screen.main;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface MainView extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
