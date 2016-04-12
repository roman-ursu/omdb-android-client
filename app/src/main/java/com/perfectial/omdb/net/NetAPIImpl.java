package com.perfectial.omdb.net;

import com.perfectial.omdb.net.response.SearchResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by rursu on 08.04.16.
 */
public class NetAPIImpl implements NetAPI {

    private OpenDBAPI openDBAPI;

    public NetAPIImpl(OpenDBAPI openDBAPI) {
        this.openDBAPI = openDBAPI;
    }

    @Override
    public Observable<SearchResponse> searchForMovie(Map<String, String> options) {
        return openDBAPI.getMovies(options);
    }
}
