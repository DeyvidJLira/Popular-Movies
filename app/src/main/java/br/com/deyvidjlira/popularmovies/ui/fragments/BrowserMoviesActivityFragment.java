package br.com.deyvidjlira.popularmovies.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.deyvidjlira.popularmovies.R;
import br.com.deyvidjlira.popularmovies.data.models.Movie;
import br.com.deyvidjlira.popularmovies.data.models.ResponseAPI;
import br.com.deyvidjlira.popularmovies.data.services.IMovieService;
import br.com.deyvidjlira.popularmovies.data.services.MovieClient;
import br.com.deyvidjlira.popularmovies.ui.activities.DetailMovieActivity;
import br.com.deyvidjlira.popularmovies.ui.adapters.MovieAdapter;
import br.com.deyvidjlira.popularmovies.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrowserMoviesActivityFragment extends Fragment {

    private MovieAdapter m_MovieAdapter;
    private IMovieService m_MovieService;
    private List<Movie> m_Movies;
    private ProgressDialog m_ProgressDialog;
    private String m_SortType = Constants.SORT_POPULAR_PARAM;
    private MenuItem m_MenuItemSortPopular;
    private MenuItem m_MenuItemSortRating;

    public BrowserMoviesActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browser_movies, container, false);

        m_ProgressDialog = new ProgressDialog(getContext());
        m_ProgressDialog.setTitle(R.string.loading);

        m_Movies = new ArrayList<>();
        m_MovieAdapter = new MovieAdapter(getActivity(), m_Movies);

        GridView gridViewMovie = (GridView) rootView.findViewById(R.id.gridViewMovie);
        gridViewMovie.setAdapter(m_MovieAdapter);
        m_MovieService = MovieClient.createService(IMovieService.class);

        gridViewMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class).putExtra("MOVIE", m_Movies.get(position));
                startActivity(intent);
            }
        });

        if(isOnline()) {
           m_ProgressDialog.show();

            fetchMovies(m_SortType);
        }

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_browser_movies, menu);

        m_MenuItemSortPopular = menu.findItem(R.id.action_sort_popular);
        m_MenuItemSortRating = menu.findItem(R.id.action_sort_rating);

        if(m_SortType.contentEquals(Constants.SORT_POPULAR_PARAM)) {
            if(!m_MenuItemSortPopular.isChecked()) {
                m_MenuItemSortPopular.setChecked(true);
            }
        } else if(m_SortType.contentEquals(Constants.SORT_RATE_PARAM)) {
            if(!m_MenuItemSortRating.isChecked()) {
                m_MenuItemSortRating.setChecked(true);
            }
        }

    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.action_sort_popular:
                if(isOnline()) {
                    m_ProgressDialog.show();
                    changeSort(Constants.SORT_POPULAR_PARAM);
                    if(!m_MenuItemSortPopular.isChecked()) {
                        m_MenuItemSortPopular.setChecked(true);
                    }
                }
                return true;
            case R.id.action_sort_rating:
                if (isOnline()) {
                    m_ProgressDialog.show();
                    changeSort(Constants.SORT_RATE_PARAM);
                    if(!m_MenuItemSortRating.isChecked()) {
                        m_MenuItemSortRating.setChecked(true);
                    }
                }
                return true;
            default:
                return true;
        }
    }

    private void changeSort(String sort) {
        m_SortType = sort;
        fetchMovies(m_SortType);
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Toast.makeText(getContext(), getString(R.string.message_offline), Toast.LENGTH_SHORT).show();
            m_ProgressDialog.dismiss();
            return false;
        }
        return true;
    }

    public void fetchMovies(String sort) {
        Call<ResponseAPI<Movie>> moviesCall = m_MovieService.getMovies(sort);

        moviesCall.enqueue(new Callback<ResponseAPI<Movie>>() {
            @Override
            public void onResponse(Call<ResponseAPI<Movie>> call, Response<ResponseAPI<Movie>> response) {
                List<Movie> movieList = response.body().getResults();
                m_MovieAdapter.clear();
                for(Movie movie : movieList) {
                    m_MovieAdapter.add(movie);
                }
                m_ProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseAPI<Movie>> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.message_offline), Toast.LENGTH_SHORT).show();
                m_ProgressDialog.dismiss();
            }
        });
    }
}