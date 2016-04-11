package com.perfectial.omdb.net;

import android.database.Observable;

import com.perfectial.omdb.net.bean.OpenDBMovieEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by rursu on 08.04.16.
 */
public interface OpenDBAPI {

    @GET
    Observable<List<OpenDBMovieEntity>> getMovies(@QueryMap Map<String, String> options);
}
