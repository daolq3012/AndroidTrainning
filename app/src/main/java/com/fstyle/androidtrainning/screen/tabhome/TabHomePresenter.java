package com.fstyle.androidtrainning.screen.tabhome;

/**
 * Listens to user actions from the UI ({@link TabHomeFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TabHomePresenter implements TabHomeContract.Presenter {
    public static final String TAG = TabHomePresenter.class.getName();

     final TabHomeContract.HomeView mHomeView;

     TabHomePresenter(TabHomeContract.HomeView view) {
        mHomeView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
