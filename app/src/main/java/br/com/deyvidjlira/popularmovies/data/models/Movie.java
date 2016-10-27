package br.com.deyvidjlira.popularmovies.data.models;

import br.com.deyvidjlira.popularmovies.util.Constants;

/**
 * Created by Deyvid on 25/10/2016.
 */
public class Movie {
    private int id;
    private String title;
    private String original_title;
    private String poster_path;
    private String release_date;
    private String overview;
    private double popularity;
    private double voteAverage;

    public Movie(int id, String title, String original_title, String poster_path, String release_date, String overview, double popularity, double voteAverage) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.overview = overview;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getImageURL() {
        return Constants.IMAGE_BASE_URL + Constants.IMAGE_SMALL_SIZE + getPosterPath();
    }
}
