package com.roman.omdb.db;

import com.j256.ormlite.dao.Dao;
import com.roman.omdb.domain.bean.OpenDBMovie;

import java.sql.SQLException;

/**
 * Created by rursu on 18.04.16.
 */
public interface DBManager {
    Dao<OpenDBMovie, String> getMovieDao() throws SQLException;
}
