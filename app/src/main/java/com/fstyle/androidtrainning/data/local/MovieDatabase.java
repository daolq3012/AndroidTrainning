package com.fstyle.androidtrainning.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.fstyle.androidtrainning.data.local.dao.MoviesDAO;
import com.fstyle.androidtrainning.data.local.entity.MovieEntity;

/**
 * Created by huynh on 19/12/2017.
 */

@Database(entities = { MovieEntity.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static final String DB_NAME = "movie.db";

    public abstract MoviesDAO getMovieDAO();

    public static final MovieDatabase initDatabase(Context context) {
        return Room.databaseBuilder(context, MovieDatabase.class, DB_NAME).build();
    }
}
