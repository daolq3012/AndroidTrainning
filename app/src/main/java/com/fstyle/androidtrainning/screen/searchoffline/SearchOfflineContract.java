package com.fstyle.androidtrainning.screen.searchoffline;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchOfflineContract {
    /**
     * View.
     */
    interface Viewer extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<Viewer> {
    }
}
