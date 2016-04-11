package com.perfectial.omdb.search;

import com.perfectial.omdb.domain.SearchManager;

/**
 * Created by rursu on 08.04.16.
 */
public class SearchPresenterImpl implements SearchPresenter {

    private SearchView searchView;
    private SearchManager searchManager;

    public SearchPresenterImpl(SearchManager searchManager) {
        this.searchManager = searchManager;
    }

    @Override
    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void onViewCreated() {
        if (searchView != null) {
            searchView.showPreloader();
        }

        searchManager.search();
    }
}
