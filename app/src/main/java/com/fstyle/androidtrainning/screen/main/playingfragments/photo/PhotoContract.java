package com.fstyle.androidtrainning.screen.main.playingfragments.photo;

/**
 * Created by Administrator on 12/10/17.
 */

public interface PhotoContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
