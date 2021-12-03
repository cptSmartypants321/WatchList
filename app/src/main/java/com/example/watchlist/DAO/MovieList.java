package com.example.watchlist.DAO;

import com.example.watchlist.Model.Movie;
import com.example.watchlist.Model.WatchList;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MovieList {
    private String imdbId;

    public MovieList(){}

    public MovieList(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }







    }


