package com.fstyle.androidtrainning.data.local.roomdb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import java.util.List;

/**
 * Created by Administrator on 12/08/17.
 */

@Dao
public interface TrackDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void doInsertTrack(TrackEntity track);

    @Query("SELECT * FROM tracks")
    List<TrackEntity> getListTrack();

    @Query("DELETE FROM tracks WHERE name = :name and artist = :artist")
    void doDeleteTrack(String name, String artist);
}
