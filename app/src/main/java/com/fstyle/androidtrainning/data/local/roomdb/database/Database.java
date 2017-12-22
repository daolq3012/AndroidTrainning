package com.fstyle.androidtrainning.data.local.roomdb.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.fstyle.androidtrainning.data.local.roomdb.dao.TrackDAO;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;

/**
 * Created by Administrator on 12/08/17.
 */

@android.arch.persistence.room.Database(entities = { TrackEntity.class }, version = 1)
public abstract class Database extends RoomDatabase {

    private static final String DB_NAME = "favoritetrack.db";

    public abstract TrackDAO getTrackDAO();

    public static Database initDatabase(Context context) {
        return Room.databaseBuilder(context, Database.class, DB_NAME).build();
    }
}
