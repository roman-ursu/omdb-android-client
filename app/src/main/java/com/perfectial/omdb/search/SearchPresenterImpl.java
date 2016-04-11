package com.perfectial.omdb.search;

import com.perfectial.omdb.domain.SearchManager;
import com.perfectial.omdb.net.bean.OpenDBMovieEntity;

import java.util.List;

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

        searchManager.search(searchCallback());
    }

    private SearchManager.MoviesListener searchCallback() {
        return new SearchManager.MoviesListener() {
            @Override
            public void onLoaded(List<OpenDBMovieEntity> movies) {
                if (searchView != null) {
                    searchView.showMovies(movies);
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        };
    }

}
