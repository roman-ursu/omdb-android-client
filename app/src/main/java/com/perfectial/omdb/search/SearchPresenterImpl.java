package com.perfectial.omdb.search;

/**
 * Created by rursu on 08.04.16.
 */
public class SearchPresenterImpl implements SearchPresenter {

    private SearchView searchView;

    @Override
    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }
}
