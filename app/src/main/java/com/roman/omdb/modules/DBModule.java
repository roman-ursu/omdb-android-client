package com.roman.omdb.modules;

import com.roman.omdb.db.DBManager;
import com.roman.omdb.db.DataBaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rursu on 12.04.16.
 */
@Module
public class DBModule {

    private DataBaseHelper dataBaseHelper;

    public DBModule(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }

    @Provides @Singleton
    public DBManager provideDataBaseHelper() {
        return this.dataBaseHelper;
    }
}
