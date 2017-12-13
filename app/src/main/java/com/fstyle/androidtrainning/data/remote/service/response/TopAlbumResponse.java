package com.fstyle.androidtrainning.data.remote.service.response;

import com.fstyle.androidtrainning.model.TopAlbums;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/12/17.
 */

public class TopAlbumResponse {

    @SerializedName("topalbums")
    @Expose
    private TopAlbums topAlbums;

    public TopAlbums getTopAlbums() {
        return topAlbums;
    }

    public void setTopAlbums(TopAlbums topAlbums) {
        this.topAlbums = topAlbums;
    }
}
