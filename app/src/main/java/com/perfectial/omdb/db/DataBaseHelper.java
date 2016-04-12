package com.perfectial.omdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.perfectial.omdb.domain.bean.OpenDBMovie;

import java.sql.SQLException;

/**
 * Created by rursu on 12.04.16.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private final static String TAG = DataBaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "openmovie";
    private static final int DATABASE_VERSION = 1;

    private Dao<OpenDBMovie, String> movieDao = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(TAG, "DB creation");
            TableUtils.createTable(connectionSource, OpenDBMovie.class);

        } catch (SQLException e) {
            Log.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(TAG, "DB upgrade");
            TableUtils.dropTable(connectionSource, OpenDBMovie.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<OpenDBMovie, String> getMovieDao() throws SQLException {
        if (movieDao == null) {
            movieDao = getDao(OpenDBMovie.class);
        }

        return movieDao;
    }

    @Override
    public void close() {
        super.close();
        movieDao = null;
    }
}
