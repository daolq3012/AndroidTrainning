package com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.toptrack;

/**
 * Created by Administrator on 12/10/17.
 */

public interface TopTrackContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
