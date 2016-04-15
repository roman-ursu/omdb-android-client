package com.perfectial.omdb.ui.search;

import java.util.Map;

/**
 * Created by rursu on 08.04.16.
 */
public interface SearchPresenter {

    void setSearchView(SearchView searchView);

    void onViewCreated();

    void onFilterClicked();

    void loadMovies(Map<String, String> options);
}
