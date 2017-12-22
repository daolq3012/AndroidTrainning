package com.fstyle.androidtrainning.data.local.roomdb.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 12/08/17.
 */

@Entity(tableName = "tracks")
public class TrackEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "name")
    private String mNameSong;
    @ColumnInfo(name = "artist")
    private String mNameArtist;
    @ColumnInfo(name = "path")
    private String mPath;

    public TrackEntity() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNameSong() {
        return mNameSong;
    }

    public void setNameSong(String nameSong) {
        mNameSong = nameSong;
    }

    public String getNameArtist() {
        return mNameArtist;
    }

    public void setNameArtist(String nameArtist) {
        mNameArtist = nameArtist;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }
}
