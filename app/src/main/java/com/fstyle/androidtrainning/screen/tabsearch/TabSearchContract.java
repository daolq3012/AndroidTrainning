package com.fstyle.androidtrainning.screen.tabsearch;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TabSearchContract {
    /**
     * View.
     */
    interface SearchView extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<SearchView> {
    }
}
