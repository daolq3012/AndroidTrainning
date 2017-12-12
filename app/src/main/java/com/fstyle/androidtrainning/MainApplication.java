package com.fstyle.androidtrainning;

import android.app.Application;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.data.service.config.ServiceGenerators;

/**
 * Created by Admin on 12/08/17.
 */

public class MainApplication extends Application {
    private static MoviesApi mMoviesApi;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mMoviesApi == null) {
            mMoviesApi = ServiceGenerators.createApiService(this);
        }
    }

    public static MoviesApi getGitHubApi() {
        return mMoviesApi;
    }
}
