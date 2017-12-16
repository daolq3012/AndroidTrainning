package com.fstyle.androidtrainning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Administrator on 12/15/17.
 */

public class Albummatches {
    @SerializedName("album")
    @Expose
    private List<SearchAlbum> album = null;

    public List<SearchAlbum> getAlbum() {
        return album;
    }

    public void setAlbum(List<SearchAlbum> album) {
        this.album = album;
    }
}
