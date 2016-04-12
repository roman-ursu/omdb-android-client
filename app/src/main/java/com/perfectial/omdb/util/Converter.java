package com.perfectial.omdb.util;

import com.perfectial.omdb.domain.bean.OpenDBMovie;
import com.perfectial.omdb.net.bean.OpenDBMovieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rursu on 12.04.16.
 */
public class Converter {
    public static List<OpenDBMovie> convert(List<OpenDBMovieEntity> openDBMovieEntities) {
        List<OpenDBMovie> movies = new ArrayList<>();
        if (openDBMovieEntities != null && openDBMovieEntities.size() > 0) {
            for (OpenDBMovieEntity openDBMovieEntity : openDBMovieEntities) {
                movies.add(convert(openDBMovieEntity));
            }
        }

        return movies;
    }

    public static OpenDBMovie convert(OpenDBMovieEntity openDBMovieEntity) {
        OpenDBMovie movie = new OpenDBMovie();
        movie.setImdbID(openDBMovieEntity.getImdbID());
        movie.setPoster(openDBMovieEntity.getPoster());
        movie.setTitle(openDBMovieEntity.getTitle());
        movie.setType(openDBMovieEntity.getType());
        movie.setYear(openDBMovieEntity.getYear());

        return movie;
    }
}
