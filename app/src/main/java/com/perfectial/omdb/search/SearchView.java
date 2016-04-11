package com.perfectial.omdb.search;

import com.perfectial.omdb.net.bean.OpenDBMovieEntity;

import java.util.List;

/**
 * Created by rursu on 08.04.16.
 */
public interface SearchView {
    void showPreloader();
    void showMovies(List<OpenDBMovieEntity> movies);

}
