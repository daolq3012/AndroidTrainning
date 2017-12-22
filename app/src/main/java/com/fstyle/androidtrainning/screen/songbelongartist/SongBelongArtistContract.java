package com.fstyle.androidtrainning.screen.songbelongartist;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SongBelongArtistContract {
    /**
     * View.
     */
    interface Viewer extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<Viewer> {
    }
}
