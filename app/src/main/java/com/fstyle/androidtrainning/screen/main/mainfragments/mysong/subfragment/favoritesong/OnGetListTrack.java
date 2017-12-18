package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import java.util.List;

/**
 * Created by Administrator on 12/18/17.
 */

public interface OnGetListTrack {

    void onGetListSuccess(List<TrackEntity> tracks);
}
