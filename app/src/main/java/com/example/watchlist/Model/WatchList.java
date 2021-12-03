package com.example.watchlist.Model;

import java.util.ArrayList;

public class WatchList {
    private ArrayList<Movie> movies;

    public WatchList(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public WatchList() {
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
