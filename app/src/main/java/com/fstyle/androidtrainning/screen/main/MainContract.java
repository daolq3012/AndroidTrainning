package com.fstyle.androidtrainning.screen.main;

/**
 * Created by Administrator on 12/10/17.
 */

public interface MainContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
