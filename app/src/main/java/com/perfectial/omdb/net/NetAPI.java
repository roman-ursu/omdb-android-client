package com.perfectial.omdb.net;

import com.perfectial.omdb.net.bean.OpenDBMovieEntity;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by rursu on 08.04.16.
 */
public interface NetAPI {
    Observable<List<OpenDBMovieEntity>> searchForMovie(Map<String, String> options);
}
