package com.fstyle.androidtrainning.screen.detailsmovie;

/**
 * Listens to user actions from the UI, retrieves the data and updates
 * the UI as required.
 */
final class DetailsMoviePresenter implements DetailsMovieContract.Presenter {
    private static final String TAG = DetailsMoviePresenter.class.getName();

    private DetailsMovieContract.DetailsMovieView mDetailsMovieView;

    DetailsMoviePresenter() {
    }

    @Override
    public void setView(DetailsMovieContract.DetailsMovieView detailsMovieView) {
        mDetailsMovieView = detailsMovieView;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
