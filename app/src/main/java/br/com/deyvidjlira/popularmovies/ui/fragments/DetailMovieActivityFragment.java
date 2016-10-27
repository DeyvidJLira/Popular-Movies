package br.com.deyvidjlira.popularmovies.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.deyvidjlira.popularmovies.R;
import br.com.deyvidjlira.popularmovies.data.models.Movie;
import br.com.deyvidjlira.popularmovies.data.services.IMovieService;
import br.com.deyvidjlira.popularmovies.util.Constants;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailMovieActivityFragment extends Fragment {

    private TextView m_TextViewTitle;
    private TextView m_TextViewReleaseDate;
    private TextView m_TextViewRating;
    private TextView m_TextViewOverview;

    private ImageView m_ImageViewPoster;

    private Button m_ButtonFavorite;

    private Movie m_Movie;
    private IMovieService m_MovieService;

    public DetailMovieActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_movie, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(view);

        m_Movie = getActivity().getIntent().getExtras().getParcelable("MOVIE");
        if(m_Movie != null) {
            m_TextViewTitle.setText(m_Movie.getOriginalTitle());
            m_TextViewReleaseDate.setText(m_Movie.getMovieReleaseYear());
            m_TextViewRating.setText(m_Movie.getRating());
            m_TextViewOverview.setText(m_Movie.getOverview());
            Picasso.with(getContext()).load(m_Movie.getImageURL(Constants.IMAGE_MINI_SIZE)).into(m_ImageViewPoster);
        }

        m_ButtonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Not implemented", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViewsById(View view) {
        m_TextViewTitle = (TextView) view.findViewById(R.id.textViewId);
        m_TextViewReleaseDate = (TextView) view.findViewById(R.id.textViewReleaseDate);
        m_TextViewRating = (TextView) view.findViewById(R.id.textViewMovieRating);
        m_TextViewOverview = (TextView) view.findViewById(R.id.textViewOverview);

        m_ImageViewPoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);

        m_ButtonFavorite = (Button) view.findViewById(R.id.buttonFavorite);
    }
}
