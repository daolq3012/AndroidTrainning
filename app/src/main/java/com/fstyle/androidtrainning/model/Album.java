package com.fstyle.androidtrainning.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/12/17.
 */

public class Album implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;
    private Drawable drawableAlbum;
    private String nameArtist;

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public Drawable getBmAlbum() {
        return drawableAlbum;
    }

    public void setBmAlbum(Drawable drawableAlbum) {
        this.drawableAlbum = drawableAlbum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public Album() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.playcount);
        dest.writeString(this.mbid);
        dest.writeString(this.url);
        dest.writeParcelable(this.artist, flags);
        dest.writeList(this.image);
        dest.writeParcelable(this.attr, flags);
        dest.writeParcelable((Parcelable) this.drawableAlbum, flags);
        dest.writeString(this.nameArtist);
    }

    protected Album(Parcel in) {
        this.name = in.readString();
        this.playcount = in.readString();
        this.mbid = in.readString();
        this.url = in.readString();
        this.artist = in.readParcelable(Artist.class.getClassLoader());
        this.image = new ArrayList<Image>();
        in.readList(this.image, Image.class.getClassLoader());
        this.attr = in.readParcelable(Attr.class.getClassLoader());
        this.drawableAlbum = in.readParcelable(Drawable.class.getClassLoader());
        this.nameArtist = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}