package com.fstyle.androidtrainning.screen.songbelongartist;

/**
 * Listens to user actions from the UI ({@link SongBelongArtistActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SongBelongArtistPresenter implements SongBelongArtistContract.Presenter {
    private static final String TAG = SongBelongArtistPresenter.class.getName();

    private SongBelongArtistContract.Viewer mView;

    @Override
    public void setView(SongBelongArtistContract.Viewer view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
