package com.fstyle.androidtrainning.service.config;

import android.content.Context;
import android.support.annotation.NonNull;
import com.fstyle.androidtrainning.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 12/08/17.
 */

public class ServiceGenerators {
    public static final long TIMEOUT_CONNECTION = TimeUnit.MINUTES.toMillis(1);

    public static LastFmApi createApiService(@NonNull Context context, String url) {
        Retrofit retrofit = createRetrofit(context, url);
        return retrofit.create(LastFmApi.class);
    }

    private static Retrofit createRetrofit(@NonNull Context context, String url) {
        //Gson rule
        Gson gson =
                new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();

        //init OkHttpClient
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        int cacheSize = 10 * 1024 * 1024; //10 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        httpClientBuilder.cache(cache);
        httpClientBuilder.readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);

        //show log when debug build
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            httpClientBuilder.addInterceptor(logging);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient okHttpClient = httpClientBuilder.build();

        return new Retrofit.Builder().baseUrl(url) // url : link api
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }
}
