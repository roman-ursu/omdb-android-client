package com.perfectial.omdb.modules;

import android.content.Context;

import com.perfectial.omdb.modules.AppModule;
import com.perfectial.omdb.net.NetAPI;
import com.perfectial.omdb.net.NetAPIImpl;
import com.perfectial.omdb.net.OpenDBAPI;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by rursu on 08.04.16.
 */
@Module(includes = {AppModule.class})
public class NetAPIModule {

    private static final String OPEN_DB_BASE_URL = "http://www.omdbapi.com";
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(OPEN_DB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Context context) {
        File cacheDir = new File(context.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder()
                .connectTimeout(30, SECONDS)
                .readTimeout(30, SECONDS)
                .writeTimeout(30, SECONDS)
                .cache(cache)
                .build();
    }

    @Provides @Singleton
    public OpenDBAPI provideOpenDBAPI(Retrofit retrofit) {
        return retrofit.create(OpenDBAPI.class);
    }

    @Provides @Singleton
    public NetAPI provideNetAPI(OpenDBAPI openDBAPI) {
        return new NetAPIImpl(openDBAPI);
    }
}