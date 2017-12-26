package com.fstyle.androidtrainning.screen.songbelongalbum;

/**
 * Listens to user actions from the UI ({@link SongBelongAlbumActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SongBelongAlbumPresenter implements SongBelongAlbumContract.Presenter {
    private static final String TAG = SongBelongAlbumPresenter.class.getName();

    private SongBelongAlbumContract.Viewer mView;

    @Override
    public void setView(SongBelongAlbumContract.Viewer view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
