package br.com.deyvidjlira.popularmovies.util;

import java.util.List;

import br.com.deyvidjlira.popularmovies.data.models.Movie;

/**
 * Created by Deyvid on 04/01/2017.
 */
public interface AsyncDelegate {
    public void asyncInit();
    public void asyncComplete(List<Movie> movieList);
}
