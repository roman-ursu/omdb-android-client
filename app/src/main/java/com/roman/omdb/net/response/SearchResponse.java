package com.roman.omdb.net.response;

import com.google.gson.annotations.SerializedName;
import com.roman.omdb.net.bean.OpenDBMovieEntity;

import java.util.List;

/**
 * Created by rursu on 12.04.16.
 */
public class SearchResponse {

    @SerializedName("Search")
    private List<OpenDBMovieEntity> collection;

    public List<OpenDBMovieEntity> getCollection() {
        return collection;
    }
}
