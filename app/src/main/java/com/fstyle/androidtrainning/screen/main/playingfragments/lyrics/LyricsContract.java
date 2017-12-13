package com.fstyle.androidtrainning.screen.main.playingfragments.lyrics;

/**
 * Created by Administrator on 12/10/17.
 */

public interface LyricsContract {

    interface Viewer {

    }

    interface Presenter {

        void setViewer(Viewer viewer);
    }
}
