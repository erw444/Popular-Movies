package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.Utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    private Movie mMovie;

    /* Field to store our TextView */
    private TextView mTitleText;
    private ImageView mPosterView;
    private TextView mReleaseDateText;
    private TextView mAverageRatingText;
    private TextView mPlotText;

    public static final String EXTRA_MOVIE = "extra_movie";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mPosterView = findViewById(R.id.iv_movie_poster_detail);
        mTitleText = findViewById(R.id.tv_detail_title);
        mReleaseDateText = findViewById(R.id.tv_detail_release_date);
        mAverageRatingText = findViewById(R.id.tv_detail_average_rating);
        mPlotText = findViewById(R.id.tv_detail_plot);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        mMovie = intent.getParcelableExtra(EXTRA_MOVIE);
        if (mMovie == null) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        populateUI();
        URL posterRequestUrl = NetworkUtils.buildMoviePosterUrl(mMovie.getPosterPath(), NetworkUtils.SIZE.BIG);
        Picasso.with(this).load(posterRequestUrl.toString()).into(mPosterView);

        setTitle(mMovie.getTitle());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        mTitleText.setText(mMovie.getTitle());
        mReleaseDateText.setText(mMovie.getReleaseDate());
        mAverageRatingText.setText(mMovie.getUserRating());
        mPlotText.setText(mMovie.getPlotDescription());

    }
}
