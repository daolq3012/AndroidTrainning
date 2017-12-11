package com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.album;

/**
 * Created by Administrator on 12/10/17.
 */

public interface AlbumContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
