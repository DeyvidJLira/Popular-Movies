package br.com.deyvidjlira.popularmovies.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.deyvidjlira.popularmovies.R;
import br.com.deyvidjlira.popularmovies.data.models.Movie;

/**
 * Created by Deyvid on 25/10/2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_grid_item, parent, false);
        }

        ImageView moviePoster = (ImageView) convertView.findViewById(R.id.movie_poster);
        Picasso.with(getContext()).load(movie.getImageURL()).into(moviePoster);

        return convertView;
    }
}
