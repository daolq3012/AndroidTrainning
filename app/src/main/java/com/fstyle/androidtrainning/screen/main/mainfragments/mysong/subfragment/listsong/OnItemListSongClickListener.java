package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import com.fstyle.androidtrainning.model.Track;
import java.util.List;

/**
 * Created by ChuongPC on 23/12/2017.
 */

public interface OnItemListSongClickListener {

    void onItemClicked(int position, List<Track> tracks);
}
