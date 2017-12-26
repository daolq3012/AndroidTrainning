package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import java.util.List;

/**
 * Created by ChuongPC on 24/12/2017.
 */

public interface OnItemFavoriteClickListener {

    void onItemFavClicked(int position, List<TrackEntity> tracks);
}
