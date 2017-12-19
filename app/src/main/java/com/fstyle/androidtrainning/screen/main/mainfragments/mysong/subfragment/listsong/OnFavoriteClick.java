package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import com.fstyle.androidtrainning.model.Track;

/**
 * Created by Administrator on 12/18/17.
 */

public interface OnFavoriteClick {

    void onFavoriteClicked(Track track);

    void onUnFavoriteClicked(Track track);
}
