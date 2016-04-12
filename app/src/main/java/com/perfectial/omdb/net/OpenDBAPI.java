package com.perfectial.omdb.net;

import com.perfectial.omdb.net.response.SearchResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by rursu on 08.04.16.
 */
public interface OpenDBAPI {

    @GET("http://www.omdbapi.com")
    Observable<SearchResponse> getMovies(@QueryMap Map<String, String> options);
}
