package com.perfectial.omdb.ui.search;

import com.perfectial.omdb.domain.SearchManager;
import com.perfectial.omdb.domain.bean.OpenDBMovie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> options = new HashMap<>();
        options.put(OpenDBMovie.TYPE_FIELD, "movie");
        options.put(OpenDBMovie.TITLE_FIELD, "Happy");
        loadMovies(options);
    }

    @Override
    public void onFilterClicked() {
        searchView.showFilterDialog();
    }

    @Override
    public void loadMovies(Map<String, String> options) {
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
