package com.fstyle.androidtrainning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Administrator on 12/15/17.
 */

public class Trackmatches {

    @SerializedName("track")
    @Expose
    private List<SearchTrack> track = null;

    public List<SearchTrack> getTrack() {
        return track;
    }

    public void setTrack(List<SearchTrack> track) {
        this.track = track;
    }

}
