package com.fstyle.androidtrainning.screen.tabsearch;

/**
 * Listens to user actions from the UI ({@link TabSearchFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TabSearchPresenter implements TabSearchContract.Presenter {
    private static final String TAG = TabSearchPresenter.class.getName();

    TabSearchContract.SearchView mSearchView;

    TabSearchPresenter() {
    }

    @Override
    public void setView(TabSearchContract.SearchView view) {
        mSearchView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
