package com.fstyle.androidtrainning.screen.searchoffline;

/**
 * Listens to user actions from the UI ({@link SearchOfflineFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SearchOfflinePresenter implements SearchOfflineContract.Presenter {
    private static final String TAG = SearchOfflinePresenter.class.getName();

    private SearchOfflineContract.Viewer mView;

    @Override
    public void setView(SearchOfflineContract.Viewer view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
