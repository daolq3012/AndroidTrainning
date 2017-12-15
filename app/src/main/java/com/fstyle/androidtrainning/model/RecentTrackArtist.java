package com.fstyle.androidtrainning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phong on 12/14/17.
 */

public class RecentTrackArtist {
    @SerializedName("#text")
    @Expose
    private String text;
    @SerializedName("mbid")
    @Expose
    private String mbid;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
