package com.fstyle.androidtrainning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Administrator on 12/12/17.
 */

public class RecentTrack {
    @SerializedName("artist")
    @Expose
    private RecentTrackArtist artist;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("streamable")
    @Expose
    private String streamable;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("album")
    @Expose
    private Album album;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("date")
    @Expose
    private Date date;

    public RecentTrackArtist getArtist() {
        return artist;
    }

    public void setArtist(RecentTrackArtist artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
