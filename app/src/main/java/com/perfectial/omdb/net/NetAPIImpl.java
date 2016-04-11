package com.perfectial.omdb.net;

import android.database.Observable;

import com.perfectial.omdb.net.bean.OpenDBMovieEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by rursu on 08.04.16.
 */
public class NetAPIImpl implements NetAPI {

    private OpenDBAPI openDBAPI;

    public NetAPIImpl(OpenDBAPI openDBAPI) {
        this.openDBAPI = openDBAPI;
    }

    @Override
    public Observable<List<OpenDBMovieEntity>> searchForMovie(Map<String, String> options) {
        return openDBAPI.getMovies(options);
    }
}
