package com.roman.omdb.ui.search;

import com.roman.omdb.domain.SearchManager;
import com.roman.omdb.domain.bean.OpenDBMovie;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by rursu on 08.04.16.
 */
public class SearchPresenterImpl implements SearchPresenter {

    private SearchView searchView;
    private SearchManager searchManager;
    private Map<String, String> lastOptions;

    public SearchPresenterImpl(SearchManager searchManager) {
        this.searchManager = searchManager;
    }

    @Override
    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void onViewCreated() {
        if (lastOptions != null) {
            loadMovies(lastOptions);
        } else {
            if (searchView != null) {
                searchView.showMovies(Collections.<OpenDBMovie>emptyList());
            }
        }
    }

    @Override
    public void onFilterClicked() {
        searchView.showFilterDialog();
    }

    @Override
    public void loadMovies(Map<String, String> options) {
        lastOptions = options;
        if (searchView != null) {
            searchView.showPreloader();
        }

        searchManager.search(searchCallback(), options);
    }

    private SearchManager.MoviesLoaderListener searchCallback() {
        return new SearchManager.MoviesLoaderListener() {
            @Override
            public void onLoaded(List<OpenDBMovie> movies) {
                if (searchView != null) {
                    searchView.showMovies(movies);
                }
            }

            @Override
            public void onError(String errorMessage) {
                searchView.showError(errorMessage);
            }
        };
    }

}
