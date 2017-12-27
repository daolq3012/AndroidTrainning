package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album;

import com.fstyle.androidtrainning.model.Album;
import java.util.List;

/**
 * Created by ChuongPC on 27/12/2017.
 */

public interface OnLoadAlbumListener {

    void onLoadAlbumSuccess(List<Album> albums);
}
