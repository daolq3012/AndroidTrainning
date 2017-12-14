package com.fstyle.androidtrainning.screen.tabfavorite;

/**
 * Listens to user actions from the UI ({@link TabFavoriteFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TabFavoritePresenter implements TabFavoriteContract.Presenter {
    private static final String TAG = TabFavoritePresenter.class.getName();

     final TabFavoriteContract.FavoriteView mFavoriteView;

     TabFavoritePresenter(TabFavoriteContract.FavoriteView view) {
        mFavoriteView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
