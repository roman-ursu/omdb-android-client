package com.roman.omdb.components;

import com.roman.omdb.modules.SearchModule;
import com.roman.omdb.ui.search.SearchActivity;

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
