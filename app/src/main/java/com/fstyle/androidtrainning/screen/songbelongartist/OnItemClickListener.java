package com.fstyle.androidtrainning.screen.songbelongartist;

import com.fstyle.androidtrainning.model.Track;
import java.util.List;

/**
 * Created by ChuongPC on 23/12/2017.
 */

public interface OnItemClickListener {

    void onItemClicked(int position, List<Track> tracks);
}
