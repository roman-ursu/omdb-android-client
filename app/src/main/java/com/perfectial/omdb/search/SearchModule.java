package com.perfectial.omdb.search;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rursu on 08.04.16.
 */
@Module
public class SearchModule {

    @Provides SearchPresenter provideSearchPresenter() {
        return new SearchPresenterImpl();
    }


}
