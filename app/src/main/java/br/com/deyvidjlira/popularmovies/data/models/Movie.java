package br.com.deyvidjlira.popularmovies.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.deyvidjlira.popularmovies.util.Constants;

/**
 * Created by Deyvid on 25/10/2016.
 */
public class Movie implements Parcelable {
    private int id;
    private String title;
    private String original_title;
    private String poster_path;
    private String release_date;
    private String overview;
    private float popularity;
    private float vote_average;

    public Movie(int id, String title, String original_title, String poster_path, String release_date, String overview, float popularity, float vote_average) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.overview = overview;
        this.popularity = popularity;
        this.vote_average = vote_average;
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

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getVote_average() {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(vote_average);
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getImageURL(String size) {
        return Constants.IMAGE_BASE_URL + size + getPosterPath();
    }


    public String getRating() {
        return ""+ getVote_average() + "/" + 10;
    }

    public String getMovieReleaseYear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String year = "";
        try {
            Date newDate = format.parse(getReleaseDate());
            year = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public Movie(Parcel in) {
        id = in.readInt();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_title = in.readString();
        popularity = in.readFloat();
        vote_average = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(original_title);
        dest.writeFloat(popularity);
        dest.writeFloat(vote_average);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
