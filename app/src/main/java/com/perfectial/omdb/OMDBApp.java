package com.perfectial.omdb;

import android.app.Application;

import com.perfectial.omdb.components.DaggerDiComponent;
import com.perfectial.omdb.components.DiComponent;
import com.perfectial.omdb.modules.AppModule;

/**
 * Created by rursu on 08.04.16.
 */
public class OMDBApp extends Application {

    private static DiComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerDiComponent.builder().appModule(new AppModule(this)).build();

    }

    public static DiComponent getAppComponent() {
        return appComponent;
    }
}
