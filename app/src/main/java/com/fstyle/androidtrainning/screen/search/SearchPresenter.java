package com.fstyle.androidtrainning.screen.search;

/**
 * Listens to user actions from the UI ({@link SearchActivity}), retrieves the data and updates
 * the UI as required.
 */
final class SearchPresenter implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getName();

    private SearchContract.Viewer mView;

    @Override
    public void setView(SearchContract.Viewer view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
