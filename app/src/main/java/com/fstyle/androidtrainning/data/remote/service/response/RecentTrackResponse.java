package com.fstyle.androidtrainning.data.remote.service.response;

import com.fstyle.androidtrainning.model.RecentTracks;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/12/17.
 */

public class RecentTrackResponse {

    @SerializedName("recenttracks")
    @Expose
    private RecentTracks recentTracks;

    public RecentTracks getRecentTracks() {
        return recentTracks;
    }

    public void setRecentTracks(RecentTracks recentTracks) {
        this.recentTracks = recentTracks;
    }
}
