package com.perfectial.omdb.domain;

import android.util.Log;

import com.perfectial.omdb.net.NetAPI;
import com.perfectial.omdb.net.bean.OpenDBMovieEntity;
import com.perfectial.omdb.util.RxHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;

/**
 * Created by rursu on 11.04.16.
 */
public class SearchManager {

    private static final String TAG = SearchManager.class.toString();

    private NetAPI netAPI;

    public interface MoviesListener {
        void onLoaded(List<OpenDBMovieEntity> movies);
        void onError(String errorMessage);
    }

    public SearchManager(NetAPI netAPI) {
        this.netAPI = netAPI;
    }

    public void search(MoviesListener moviesListener) {
        Map<String, String> options = new HashMap<>();
        options.put("type", "movie");
        options.put("s", "happy");
        netAPI.searchForMovie(options)
                .compose(RxHelper.<List<OpenDBMovieEntity>>getSchedulers())
                .subscribe(listLoadCallBack(moviesListener));
    }

    private Observer<List<OpenDBMovieEntity>> listLoadCallBack(final MoviesListener moviesListener) {
        return new Observer<List<OpenDBMovieEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (moviesListener != null) {
                    Log.e(TAG, e.getMessage(), e);
                    moviesListener.onError(e.getMessage());
                }
            }

            @Override
            public void onNext(List<OpenDBMovieEntity> openDBMovieEntities) {
                if (moviesListener != null) {
                    moviesListener.onLoaded(openDBMovieEntities);
                }
            }
        };
    }
}
