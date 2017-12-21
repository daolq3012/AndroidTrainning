package com.fstyle.androidtrainning.data.local;

import com.fstyle.androidtrainning.data.local.entity.MovieEntity;
import java.util.List;

/**
 * Created by huynh on 19/12/2017.
 */

public interface OnSelectDataListener {
    void onSelectDataSuccess(List<MovieEntity> movieEntityList);
}
