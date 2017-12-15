package com.fstyle.androidtrainning.application;

import android.app.Application;
import com.fstyle.androidtrainning.data.remote.service.config.LastFmApi;
import com.fstyle.androidtrainning.data.remote.service.config.ServiceGenerators;
import com.fstyle.androidtrainning.utils.Constant;

/**
 * Created by Administrator on 12/08/17.
 */

public class MainApplication extends Application {

    private static LastFmApi mApi;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mApi == null) {
            mApi = ServiceGenerators.createApiService(this, Constant.URL);
        }
    }

    public static LastFmApi getLastFmApi() {
        return mApi;
    }
}
