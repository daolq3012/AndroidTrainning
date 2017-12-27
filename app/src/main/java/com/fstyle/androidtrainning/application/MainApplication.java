package com.fstyle.androidtrainning.application;

import android.app.Application;
import com.fstyle.androidtrainning.data.local.roomdb.database.Database;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.data.remote.service.config.ServiceGenerators;
import com.fstyle.androidtrainning.utils.Constant;

/**
 * Created by Administrator on 12/08/17.
 */

public class MainApplication extends Application {

    private static LastFmApi mApi;
    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mDatabase == null) {
            mDatabase = Database.initDatabase(this);
        }
        if (mApi == null) {
            mApi = ServiceGenerators.createApiService(this, Constant.URL);
        }
    }

    public static LastFmApi getLastFmApi() {
        return mApi;
    }

    public static Database getDatabase() {
        return mDatabase;
    }
}
