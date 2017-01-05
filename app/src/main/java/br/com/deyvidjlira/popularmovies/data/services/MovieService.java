package br.com.deyvidjlira.popularmovies.data.services;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import br.com.deyvidjlira.popularmovies.data.models.Movie;
import br.com.deyvidjlira.popularmovies.data.models.ResponseAPI;
import br.com.deyvidjlira.popularmovies.util.AsyncDelegate;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Deyvid on 05/01/2017.
 */
public class MovieService extends AsyncTask<String, Void, List<Movie>> {

    private AsyncDelegate m_Delegate;
    private IMovieService m_MovieService;
    private List<Movie> m_MovieList;

    public MovieService(AsyncDelegate delegate) {
        m_Delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        if(m_Delegate != null) {
            m_Delegate.asyncInit();
        }
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        String sort = params[0];
        m_MovieService = MovieClient.createService(IMovieService.class);

        Call<ResponseAPI<Movie>> moviesCall = m_MovieService.getMovies(sort);
        try {
            Response<ResponseAPI<Movie>> response = moviesCall.execute();
            m_MovieList = response.body().getResults();
        } catch (Exception e) {
            Log.e(MovieService.class.getSimpleName(), "Error service!");
        }
        return m_MovieList;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if(m_Delegate != null) {
            m_Delegate.asyncComplete(movies);
        }
    }

}
