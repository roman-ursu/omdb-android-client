package com.roman.omdb.modules;

import android.content.Context;

import com.roman.omdb.net.NetAPI;
import com.roman.omdb.net.NetAPIImpl;
import com.roman.omdb.net.OpenDBAPI;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Context context) {
        File cacheDir = new File(context.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(30, SECONDS)
                .readTimeout(30, SECONDS)
                .writeTimeout(30, SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
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
