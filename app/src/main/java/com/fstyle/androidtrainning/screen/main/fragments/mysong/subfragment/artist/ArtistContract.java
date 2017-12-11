package com.fstyle.androidtrainning.screen.main.fragments.mysong.subfragment.artist;

/**
 * Created by Administrator on 12/10/17.
 */

public interface ArtistContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
