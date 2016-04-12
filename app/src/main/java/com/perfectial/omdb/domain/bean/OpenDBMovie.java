package com.perfectial.omdb.domain.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rursu on 11.04.16.
 */
@DatabaseTable(tableName = "movie")
public class OpenDBMovie {

    @DatabaseField(id = true)
    private String imdbID;

    @DatabaseField
    private String title;

    @DatabaseField
    private String year;

    @DatabaseField
    private String type;

    @DatabaseField
    private String poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
