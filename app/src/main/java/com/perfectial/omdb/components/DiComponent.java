package com.perfectial.omdb.components;

import com.perfectial.omdb.search.SearchActivity;
import com.perfectial.omdb.search.SearchModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rursu on 08.04.16.
 */
@Singleton
@Component(modules = {SearchModule.class})
public interface DiComponent {
    void inject(SearchActivity searchActivity);
}
