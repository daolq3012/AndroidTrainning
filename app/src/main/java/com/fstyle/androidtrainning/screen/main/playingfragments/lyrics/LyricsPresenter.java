package com.fstyle.androidtrainning.screen.main.playingfragments.lyrics;

import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.utils.Constant;
import java.io.File;
import java.util.List;
import me.zhengken.lyricview.LyricView;

/**
 * Created by Administrator on 12/10/17.
 */

public class LyricsPresenter implements LyricsContract.Presenter {

    private LyricsContract.Viewer mViewer;

    @Override
    public void setView(LyricsContract.Viewer view) {
        mViewer = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void doLoadLyric(Track track, List<File> fileArrayList, LyricView lyricView) {
        String songPath = track.getTrackData();
        int indexSongData = songPath.lastIndexOf('/');
        String subSongData = songPath.substring(indexSongData + 1).replace(Constant.TYPE_MP3, "");
        for (int i = 0; i < fileArrayList.size(); i++) {
            int indexFileArrayList = fileArrayList.get(i).toString().lastIndexOf('/');
            String file = fileArrayList.get(i)
                    .toString()
                    .substring(indexFileArrayList + 1)
                    .replace(Constant.TYPE_LYRIC, "");
            if (subSongData.equalsIgnoreCase(file)) {
                lyricView.setLyricFile(fileArrayList.get(i));
                lyricView.invalidate();
                return;
            } else {
                lyricView.setLyricFile(null);
            }
        }
        lyricView.setLyricFile(null);
    }
}
