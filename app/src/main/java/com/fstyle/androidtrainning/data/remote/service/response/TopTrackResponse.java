package com.fstyle.androidtrainning.data.remote.service.response;

import com.fstyle.androidtrainning.model.TopTracks;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/12/17.
 */

public class TopTrackResponse {

    @SerializedName("toptracks")
    @Expose
    private TopTracks topTracks;

    public TopTracks getTopTracks() {
        return topTracks;
    }

    public void setTopTracks(TopTracks topTracks) {
        this.topTracks = topTracks;
    }
}
