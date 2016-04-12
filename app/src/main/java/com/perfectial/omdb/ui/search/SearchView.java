package com.perfectial.omdb.ui.search;

import com.perfectial.omdb.domain.bean.OpenDBMovie;

import java.util.List;

/**
 * Created by rursu on 08.04.16.
 */
public interface SearchView {
    void showPreloader();
    void showMovies(List<OpenDBMovie> movies);
    void showError(String errorMessage);

}
