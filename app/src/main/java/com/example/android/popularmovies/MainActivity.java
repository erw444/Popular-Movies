package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.Utilities.NetworkUtils;
import com.example.android.popularmovies.Utilities.OpenMovieJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mMovieAdapter;
    private ProgressBar mProgessBar;
    private TextView mErrorTextView;
    private boolean mSearchPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieRecyclerView = findViewById(R.id.recyclerview_movies);
        mProgessBar = findViewById(R.id.pb_loading_indicator);
        mErrorTextView = findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mMovieRecyclerView.setLayoutManager(layoutManager);
        mMovieAdapter = new MovieAdapter(this);
        mMovieRecyclerView.setAdapter(mMovieAdapter);

        mProgessBar = findViewById(R.id.pb_loading_indicator);

        loadMovieData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search_popular) {
            mSearchPopular = true;
            loadMovieData();
            return true;
        } else if (itemThatWasClickedId == R.id.action_search_highest_rated) {
            mSearchPopular = false;
            loadMovieData();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(Movie movie) {
        launchDetailActivity(movie);

    }

    private void launchDetailActivity(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    private void loadMovieData(){
        showMovieDataView();

        new FetchMovieTask().execute();
    }

    private void showMovieDataView() {
        /* First, make sure the error is invisible */
        mErrorTextView.setVisibility(View.INVISIBLE);
        /* Then, make sure the movie data is visible */
        mMovieRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mMovieRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgessBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {

            URL movieRequestUrl = null;
            if(mSearchPopular){
                movieRequestUrl = NetworkUtils.buildMoviesUrl(NetworkUtils.SORT.POPULAR);
            } else {
                movieRequestUrl = NetworkUtils.buildMoviesUrl(NetworkUtils.SORT.HIGHRATE);
            }


            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                Movie[] jsonMovieData = OpenMovieJsonUtils
                        .getMovieDataFromJson(MainActivity.this, jsonMovieResponse);


                return jsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movieData) {
            mProgessBar.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movieData);
            } else {
                showErrorMessage();
            }
        }
    }
}
