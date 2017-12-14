package com.fstyle.androidtrainning.screen.tabprofile;

/**
 * Listens to user actions from the UI ({@link TabProfileFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TabProfilePresenter implements TabProfileContract.Presenter {
    private static final String TAG = TabProfilePresenter.class.getName();

     final TabProfileContract.ProfileView mProfileView;

     TabProfilePresenter(TabProfileContract.ProfileView view) {
        mProfileView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
