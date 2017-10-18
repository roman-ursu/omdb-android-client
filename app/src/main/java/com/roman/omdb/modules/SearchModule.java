package com.roman.omdb.modules;

import com.roman.omdb.db.DBManager;
import com.roman.omdb.domain.SearchManager;
import com.roman.omdb.net.NetAPI;
import com.roman.omdb.ui.search.SearchPresenter;
import com.roman.omdb.ui.search.SearchPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rursu on 08.04.16.
 */
@Module(includes = {NetAPIModule.class, DBModule.class})
public class SearchModule {

    @Provides @Singleton
    public SearchPresenter provideSearchPresenter(SearchManager searchManager) {
        return new SearchPresenterImpl(searchManager);
    }

    @Provides @Singleton
    public SearchManager provideSearchManager(NetAPI netAPI, DBManager dbManager) {
        return new SearchManager(netAPI, dbManager);
    }
}
