package com.fstyle.androidtrainning.screen.detailsmovie;

/**
 * Listens to user actions from the UI, retrieves the data and updates
 * the UI as required.
 */
final class DetailsMoviePresenter implements DetailsMovieContract.Presenter {
    private static final String TAG = DetailsMoviePresenter.class.getName();

    private DetailsMovieContract.ViewDetail mViewDetail;

    DetailsMoviePresenter() {
    }

    @Override
    public void setView(DetailsMovieContract.ViewDetail viewDetail) {
        mViewDetail = viewDetail;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
