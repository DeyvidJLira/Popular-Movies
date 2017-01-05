package br.com.deyvidjlira.popularmovies.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import br.com.deyvidjlira.popularmovies.data.preferences.PopularMoviesPreference;
import br.com.deyvidjlira.popularmovies.data.preferences.PreferenceParameters;
import br.com.deyvidjlira.popularmovies.data.services.MovieService;
import br.com.deyvidjlira.popularmovies.ui.activities.DetailMovieActivity;
import br.com.deyvidjlira.popularmovies.ui.adapters.MovieAdapter;
import br.com.deyvidjlira.popularmovies.util.AsyncDelegate;
import br.com.deyvidjlira.popularmovies.util.Constants;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrowserMoviesActivityFragment extends Fragment implements AsyncDelegate {

    private MovieAdapter m_MovieAdapter;
    private List<Movie> m_Movies = new ArrayList<>();
    private ProgressDialog m_ProgressDialog;
    private String m_SortType;
    private MenuItem m_MenuItemSortPopular;
    private MenuItem m_MenuItemSortRating;
    private GridView m_GridViewMovies;

    public BrowserMoviesActivityFragment() { setHasOptionsMenu(true); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browser_movies, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            m_GridViewMovies = (GridView) view.findViewById(R.id.gridViewMovie);
        }

        m_SortType = PopularMoviesPreference.getInstance(getContext()).getString(PreferenceParameters.m_KEY_SORT, Constants.SORT_POPULAR_PARAM);

        if(isOnline()) {
            new MovieService(this).execute(m_SortType);
        }
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
                    changeSort(Constants.SORT_POPULAR_PARAM);
                    if(!m_MenuItemSortPopular.isChecked()) {
                        m_MenuItemSortPopular.setChecked(true);
                    }
                }
                return true;
            case R.id.action_sort_rating:
                if (isOnline()) {
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

    @Override
    public void asyncInit() {
        m_ProgressDialog = new ProgressDialog(getContext());
        m_ProgressDialog.setTitle(R.string.loading);
        m_ProgressDialog.setMessage(getResources().getString(R.string.message_searching_movies));
        m_ProgressDialog.show();
    }

    @Override
    public void asyncComplete(List<Movie> movies) {
        if(movies != null) {
            m_Movies = movies;
            m_MovieAdapter = new MovieAdapter(getActivity(), m_Movies);
            updateGridViewMovies();
        } else  {
            Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_LONG).show();
        }
        m_ProgressDialog.dismiss();
    }

    private void changeSort(String sort) {
        m_SortType = sort;
        PopularMoviesPreference.getInstance(getContext()).putString(PreferenceParameters.m_KEY_SORT, m_SortType);
        if(isOnline()) {
            new MovieService(this).execute(m_SortType);
        }
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Toast.makeText(getContext(), getString(R.string.message_offline), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateGridViewMovies() {
        m_GridViewMovies.setAdapter(m_MovieAdapter);
        m_GridViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class).putExtra("MOVIE", m_Movies.get(position));
                startActivity(intent);
            }
        });
    }
}