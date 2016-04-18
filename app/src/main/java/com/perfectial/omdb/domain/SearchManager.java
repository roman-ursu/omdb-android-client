package com.perfectial.omdb.domain;

import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.perfectial.omdb.db.DBManager;
import com.perfectial.omdb.db.DataBaseHelper;
import com.perfectial.omdb.domain.bean.OpenDBMovie;
import com.perfectial.omdb.net.NetAPI;
import com.perfectial.omdb.net.response.SearchResponse;
import com.perfectial.omdb.util.Converter;
import com.perfectial.omdb.util.RxHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;


/**
 * Created by rursu on 11.04.16.
 */
public class SearchManager {
    private static final String TAG = SearchManager.class.toString();

    private NetAPI netAPI;
    private DBManager dbManager;

    public interface MoviesLoaderListener {
        void onLoaded(List<OpenDBMovie> movies);
        void onError(String errorMessage);
    }

    public SearchManager(NetAPI netAPI, DBManager dbManager) {
        this.netAPI = netAPI;
        this.dbManager = dbManager;
    }

    public void search(MoviesLoaderListener moviesListener, final Map<String, String> options) {

        Observable<List<OpenDBMovie>> loadMoviesObservable = Observable.create(new Observable.OnSubscribe<List<OpenDBMovie>>() {
            @Override
            public void call(Subscriber<? super List<OpenDBMovie>> subscriber) {
                List<OpenDBMovie> moviesFromDB = loadFromDB(options);
                if (moviesFromDB.size() > 0) {
                    subscriber.onNext(moviesFromDB);
                }

                try {
                    SearchResponse searchResponse = netAPI.searchForMovieSync(options);
                    subscriber.onNext(Converter.convert(searchResponse.getCollection()));
                    subscriber.onCompleted();

                } catch (Exception e) {
                    Log.e(TAG, "Error searching movies on network", e);
                    subscriber.onError(e);
                }

            }
        });

        loadMoviesObservable
                .compose(RxHelper.<List<OpenDBMovie>>getSchedulers())
                .subscribe(loadMoviesCallBack(moviesListener));
    }

    private List<OpenDBMovie> loadFromDB(final Map<String, String> options) {
        List<OpenDBMovie> movies = new ArrayList<>();
        try {
            QueryBuilder<OpenDBMovie, String> queryBuilder = dbManager.getMovieDao().queryBuilder();
            Where where = queryBuilder.where();

            Iterator<Map.Entry<String, String>> iterator = options.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();

                if (OpenDBMovie.TITLE_FIELD.equals(entry.getKey())) {
                    where.like(OpenDBMovie.TITLE_FIELD, entry.getValue());
                } else {
                    where.eq(entry.getKey(), entry.getValue());
                }

                if (iterator.hasNext()) {
                    where.and();
                }
            }

            PreparedQuery<OpenDBMovie> preparedQuery = queryBuilder.prepare();
            Log.d(TAG, queryBuilder.prepareStatementString());

            movies.addAll(dbManager.getMovieDao().query(preparedQuery));

        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return movies;
    }

    private Observer<List<OpenDBMovie>> loadMoviesCallBack(final MoviesLoaderListener moviesListener) {
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
                    moviesListener.onLoaded(openDBMovies);
                }
            }
        };
    }

    private void saveNewMoviesToDBIfNotExist(List<OpenDBMovie> openDBMovies) {
        for (OpenDBMovie movie : openDBMovies) {
            try {
                dbManager.getMovieDao().createIfNotExists(movie);

            } catch (SQLException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }
}
