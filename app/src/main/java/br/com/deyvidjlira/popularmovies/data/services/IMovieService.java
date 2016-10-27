package br.com.deyvidjlira.popularmovies.data.services;

import br.com.deyvidjlira.popularmovies.data.models.Movie;
import br.com.deyvidjlira.popularmovies.data.models.ResponseAPI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Deyvid on 25/10/2016.
 */
public interface IMovieService {

    @GET("3/movie/{sort}?")
    Call<ResponseAPI<Movie>> getMovies(@Path("sort")String sortBy);
}
