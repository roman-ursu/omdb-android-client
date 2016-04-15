package com.perfectial.omdb.net;

import android.util.Log;

import com.perfectial.omdb.domain.bean.OpenDBMovie;
import com.perfectial.omdb.net.response.SearchResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by rursu on 08.04.16.
 */
public class NetAPIImpl implements NetAPI {

    private static final String TAG = NetAPIImpl.class.getSimpleName();
    private OpenDBAPI openDBAPI;

    public NetAPIImpl(OpenDBAPI openDBAPI) {
        this.openDBAPI = openDBAPI;
    }

    @Override
    public Observable<SearchResponse> searchForMovie(Map<String, String> options) {
        Map<String, String> convertedOptions = new HashMap<>();

        for (Map.Entry<String, String> entry : options.entrySet()) {
            switch (entry.getKey()) {
                case OpenDBMovie.TITLE_FIELD : {
                    convertedOptions.put("s", entry.getValue());
                } break;

                case OpenDBMovie.YEAR_FIELD : {
                    convertedOptions.put("y", entry.getValue());
                } break;

                case OpenDBMovie.TYPE_FIELD : {
                    convertedOptions.put("type", entry.getValue());
                } break;
            }
        }

        return openDBAPI.getMovies(convertedOptions);
    }

    @Override
    public SearchResponse searchForMovieSync(Map<String, String> options) {
        Map<String, String> convertedOptions = new HashMap<>();

        for (Map.Entry<String, String> entry : options.entrySet()) {
            switch (entry.getKey()) {
                case OpenDBMovie.TITLE_FIELD : {
                    convertedOptions.put("s", entry.getValue());
                } break;

                case OpenDBMovie.YEAR_FIELD : {
                    convertedOptions.put("y", entry.getValue());
                } break;

                case OpenDBMovie.TYPE_FIELD : {
                    convertedOptions.put("type", entry.getValue());
                } break;
            }
        }

        SearchResponse searchResponse = null;
        try {
            searchResponse = openDBAPI.getMoviesSync(convertedOptions).execute().body();

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return searchResponse;
    }
}
