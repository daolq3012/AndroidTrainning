package com.fstyle.androidtrainning.screen.tabprofile;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TabProfileContract {
    /**
     * View.
     */
    interface ProfileView extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
