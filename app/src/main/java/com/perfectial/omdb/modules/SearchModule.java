package com.perfectial.omdb.modules;

import com.perfectial.omdb.db.DBManager;
import com.perfectial.omdb.db.DataBaseHelper;
import com.perfectial.omdb.domain.SearchManager;
import com.perfectial.omdb.net.NetAPI;
import com.perfectial.omdb.ui.search.SearchPresenter;
import com.perfectial.omdb.ui.search.SearchPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rursu on 08.04.16.
 */
@Module(includes = {NetAPIModule.class, DBModule.class})
public class SearchModule {

    @Provides
    public SearchPresenter provideSearchPresenter(SearchManager searchManager) {
        return new SearchPresenterImpl(searchManager);
    }

    @Provides @Singleton
    public SearchManager provideSearchManager(NetAPI netAPI, DBManager dbManager) {
        return new SearchManager(netAPI, dbManager);
    }
}
