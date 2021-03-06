package com.roman.omdb.ui.search;

import com.roman.omdb.domain.bean.OpenDBMovie;

import java.util.List;

/**
 * Created by rursu on 08.04.16.
 */
public interface SearchView {
    void showPreloader();
    void showMovies(List<OpenDBMovie> movies);
    void showError(String errorMessage);
    void showFilterDialog();
}
