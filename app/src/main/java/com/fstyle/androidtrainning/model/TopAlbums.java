package com.fstyle.androidtrainning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Administrator on 12/12/17.
 */

public class TopAlbums {

    @SerializedName("album")
    @Expose
    private List<Album> album = null;
    @SerializedName("@attr")
    @Expose
    private Attr_ attr;

    public TopAlbums(){}

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public Attr_ getAttr() {
        return attr;
    }

    public void setAttr(Attr_ attr) {
        this.attr = attr;
    }
}
