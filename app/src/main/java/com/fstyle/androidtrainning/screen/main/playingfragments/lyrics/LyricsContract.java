package com.fstyle.androidtrainning.screen.main.playingfragments.lyrics;

import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;
import java.io.File;
import java.util.List;
import me.zhengken.lyricview.LyricView;

/**
 * Created by Administrator on 12/10/17.
 */

public interface LyricsContract {

    interface Viewer extends BaseView {

    }

    interface Presenter extends BasePresenter<Viewer> {

        void doLoadLyric(Track track, List<File> fileArrayList, LyricView lyricView);
    }
}
