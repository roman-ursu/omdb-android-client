package com.roman.omdb.net;

import com.roman.omdb.net.response.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by rursu on 08.04.16.
 */
public interface OpenDBAPI {

    @GET("http://www.omdbapi.com")
    Observable<SearchResponse> getMovies(@QueryMap Map<String, String> options);

    @GET("http://www.omdbapi.com")
    Call<SearchResponse> getMoviesSync(@QueryMap Map<String, String> options);
}
