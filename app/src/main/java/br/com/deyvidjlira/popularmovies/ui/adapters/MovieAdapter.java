package br.com.deyvidjlira.popularmovies.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.deyvidjlira.popularmovies.R;
import br.com.deyvidjlira.popularmovies.data.models.Movie;
import br.com.deyvidjlira.popularmovies.util.Constants;

/**
 * Created by Deyvid on 25/10/2016.
 */
public class MovieAdapter extends BaseAdapter {

    private Context m_Context;
    private List<Movie> m_Movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        m_Context = context;
        m_Movies = movies;
    }

    public void addMovie(Movie movie) {
        m_Movies.add(movie);
    }

    public void clear() {
        m_Movies.clear();
    }

    @Override
    public int getCount() {
        return m_Movies.size();
    }

    @Override
    public Object getItem(int i) {
        return m_Movies.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = (Movie) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(m_Context).inflate(R.layout.movie_grid_item, parent, false);
        }

        ImageView moviePoster = (ImageView) convertView.findViewById(R.id.movie_poster);
        Picasso.with(m_Context).load(movie.getImageURL(Constants.IMAGE_SMALL_SIZE)).into(moviePoster);

        return convertView;
    }
}