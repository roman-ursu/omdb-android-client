package com.perfectial.omdb.domain;

import com.perfectial.omdb.net.NetAPI;
import com.perfectial.omdb.modules.NetAPIModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rursu on 11.04.16.
 */
@Module(includes = {NetAPIModule.class})
public class DomainModule {

    @Provides @Singleton SearchManager provideSearchManager(NetAPI netAPI) {
        return new SearchManager(netAPI);
    }
}
