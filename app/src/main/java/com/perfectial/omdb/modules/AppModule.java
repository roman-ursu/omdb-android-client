package com.perfectial.omdb.modules;

import android.content.Context;

import com.perfectial.omdb.OMDBApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rursu on 11.04.16.
 */
@Module
public class AppModule {
    private OMDBApp app;

    public AppModule(OMDBApp app) {
        this.app = app;
    }

    @Provides
    Context provideContext() {
        return app.getApplicationContext();
    }
}
