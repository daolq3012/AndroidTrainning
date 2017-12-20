package com.fstyle.androidtrainning.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/12/17.
 */

public class Track implements Parcelable {

    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("streamable")
    @Expose
    private Streamable streamable;
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
    private String nameArtist;
    private Integer position;
    private String trackData;

    public String getTrackData() {
        return trackData;
    }

    public void setTrackData(String trackData) {
        this.trackData = trackData;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public void setStreamable(Streamable streamable) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.artist, flags);
        dest.writeString(this.name);
        dest.writeParcelable(this.streamable, flags);
        dest.writeString(this.mbid);
        dest.writeParcelable(this.album, flags);
        dest.writeString(this.url);
        dest.writeList(this.image);
        dest.writeParcelable(this.date, flags);
        dest.writeString(this.nameArtist);
        dest.writeValue(this.position);
        dest.writeString(this.trackData);
    }

    public Track() {
    }

    protected Track(Parcel in) {
        this.artist = in.readParcelable(Artist.class.getClassLoader());
        this.name = in.readString();
        this.streamable = in.readParcelable(Streamable.class.getClassLoader());
        this.mbid = in.readString();
        this.album = in.readParcelable(Album.class.getClassLoader());
        this.url = in.readString();
        this.image = new ArrayList<Image>();
        in.readList(this.image, Image.class.getClassLoader());
        this.date = in.readParcelable(Date.class.getClassLoader());
        this.nameArtist = in.readString();
        this.position = (Integer) in.readValue(Integer.class.getClassLoader());
        this.trackData = in.readString();
    }

    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel source) {
            return new Track(source);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };
}
