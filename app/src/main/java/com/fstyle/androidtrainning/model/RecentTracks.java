package com.fstyle.androidtrainning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Administrator on 12/12/17.
 */

public class RecentTracks {

    @SerializedName("track")
    @Expose
    private List<RecentTrack> track = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public List<RecentTrack> getTrack() {
        return track;
    }

    public void setTrack(List<RecentTrack> track) {
        this.track = track;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }
}
