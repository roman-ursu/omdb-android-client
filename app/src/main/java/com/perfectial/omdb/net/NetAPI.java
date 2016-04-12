package com.perfectial.omdb.net;

import com.perfectial.omdb.net.response.SearchResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by rursu on 08.04.16.
 */
public interface NetAPI {
    Observable<SearchResponse> searchForMovie(Map<String, String> options);
}
