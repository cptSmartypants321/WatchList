package com.example.watchlist.Model;

import java.io.Serializable;

public class Movie implements Serializable {


    private String title;
    private String year;
    private String release;
    private String runtime;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String poster;
    private String type;
    private String id;
    private String category;
    private String rating;
    private String boxOffice;


    public Movie(){};

    public Movie(String title, String year, String runtime, String director, String writer, String actors, String plot, String poster, String type, String id) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.poster = poster;
        this.type = type;
        this.id = id;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public void setTitle(String title) {
        this.title=title;
    }
    public void setId(String id){
        this.id=id;
    }



    public String getPoster() {
      
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getYear() {
        return year;
    }
    public String getID(){
        return id;
    }


    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }


}
