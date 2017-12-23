package com.fstyle.androidtrainning.screen.searchoffline;

import com.fstyle.androidtrainning.model.Track;
import java.util.List;

/**
 * Created by ChuongPC on 23/12/2017.
 */

public interface OnItemSongClickListener {
    
    void onItemSongClicked(String nameSong, String nameArtist, List<Track> tracksTemp);
}
