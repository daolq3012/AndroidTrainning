package com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.listsong;

/**
 * Created by Administrator on 12/10/17.
 */

public interface ListSongContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
