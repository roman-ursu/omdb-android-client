package com.perfectial.omdb.domain;

import android.util.Log;

import com.perfectial.omdb.db.DataBaseHelper;
import com.perfectial.omdb.domain.bean.OpenDBMovie;
import com.perfectial.omdb.net.NetAPI;
import com.perfectial.omdb.net.response.SearchResponse;
import com.perfectial.omdb.util.Converter;
import com.perfectial.omdb.util.RxHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;


/**
 * Created by rursu on 11.04.16.
 */
public class SearchManager {

    private static final String TAG = SearchManager.class.toString();

    private NetAPI netAPI;
    private DataBaseHelper dataBaseHelper;

    public interface MoviesLoaderListener {
        void onLoaded(List<OpenDBMovie> movies);
        void onNewLoaded(List<OpenDBMovie> movies);
        void onError(String errorMessage);
    }

    public SearchManager(NetAPI netAPI, DataBaseHelper dataBaseHelper) {
        this.netAPI = netAPI;
        this.dataBaseHelper = dataBaseHelper;
    }

    public void search(MoviesLoaderListener moviesListener) {
        loadFromDB().subscribe(loadMoviesFromDBCallBack(moviesListener));

        Map<String, String> options = new HashMap<>();
        options.put("s", "happy");

        netAPI.searchForMovie(options)
                .map(new Func1<SearchResponse, List<OpenDBMovie>>() {
                    @Override
                    public List<OpenDBMovie> call(SearchResponse searchResponse) {
                        return Converter.convert(searchResponse.getCollection());
                    }
                })
                .compose(RxHelper.<List<OpenDBMovie>>getSchedulers())
                .subscribe(loadMoviesFromNetCallBack(moviesListener));
    }

    private Observable<List<OpenDBMovie>> loadFromDB() {
        return Observable.create(new Observable.OnSubscribe<List<OpenDBMovie>>() {

            @Override
            public void call(Subscriber<? super List<OpenDBMovie>> subscriber) {
                try {
                    subscriber.onNext(dataBaseHelper.getMovieDao().queryForAll());
                    subscriber.onCompleted();
                } catch (SQLException e) {

                    subscriber.onError(e);
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        }).compose(RxHelper.<List<OpenDBMovie>>getSchedulers());
    }

    private Observer<List<OpenDBMovie>> loadMoviesFromNetCallBack(final MoviesLoaderListener moviesListener) {
        return new Observer<List<OpenDBMovie>>() {
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
            public void onNext(List<OpenDBMovie> openDBMovies) {

                saveNewMoviesToDBIfNotExist(openDBMovies);

                if (moviesListener != null) {
                    moviesListener.onNewLoaded(openDBMovies);
                }
            }
        };
    }

    private Observer<List<OpenDBMovie>> loadMoviesFromDBCallBack(final MoviesLoaderListener moviesListener) {
        return new Observer<List<OpenDBMovie>>() {
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
            public void onNext(List<OpenDBMovie> openDBMovies) {
                if (moviesListener != null) {
                    moviesListener.onLoaded(openDBMovies);
                }
            }
        };
    }

    private void saveNewMoviesToDBIfNotExist(List<OpenDBMovie> openDBMovies) {
        for (OpenDBMovie movie : openDBMovies) {
            try {
                dataBaseHelper.getMovieDao().createIfNotExists(movie);

            } catch (SQLException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }
}
