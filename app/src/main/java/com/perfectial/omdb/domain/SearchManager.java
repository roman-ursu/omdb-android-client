package com.perfectial.omdb.domain;

import android.util.Log;

import com.perfectial.omdb.net.NetAPI;

/**
 * Created by rursu on 11.04.16.
 */
public class SearchManager {

    private static final String TAG = SearchManager.class.toString();

    private NetAPI netAPI;

    public SearchManager(NetAPI netAPI) {
        this.netAPI = netAPI;
    }

    public void search() {
        Log.i(TAG, "Searching...");
    }
}
