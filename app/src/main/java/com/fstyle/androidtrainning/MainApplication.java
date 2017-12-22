package com.fstyle.androidtrainning;

import android.app.Application;

import com.fstyle.androidtrainning.data.local.MovieDatabase;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.data.service.config.ServiceGenerators;

/**
 * Created by Admin on 12/08/17.
 */

public class MainApplication extends Application {

    private static MoviesApi mMoviesApi;
    private static MovieDatabase mMovieDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mMoviesApi == null) {
            mMoviesApi = ServiceGenerators.createApiService(this);
        }

        mMovieDatabase = MovieDatabase.initDatabase(this);
    }

    public static MoviesApi getMoviesApi() {
        return mMoviesApi;
    }

    public static MovieDatabase getMovieDatabase() {
        return mMovieDatabase;
    }
}
