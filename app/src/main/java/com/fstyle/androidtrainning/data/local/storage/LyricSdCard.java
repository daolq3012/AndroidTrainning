package com.fstyle.androidtrainning.data.local.storage;

import com.fstyle.androidtrainning.utils.Constant;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 12/16/17.
 */

public final class LyricSdCard {

    private LyricSdCard() {
    }

    public ArrayList<File> findLyrics(File root) {
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                al.addAll(findLyrics(singleFile));
            } else {
                if (singleFile.getName().endsWith(Constant.TYPE_LYRIC)) {
                    al.add(singleFile);
                }
            }
        }
        return al;
    }
}
