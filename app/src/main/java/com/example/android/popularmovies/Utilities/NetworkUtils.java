package com.example.android.popularmovies.Utilities;

import android.net.Uri;
import android.util.Log;
import android.util.Size;

import com.example.android.popularmovies.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    public enum SIZE { SMALL, BIG; }
    public enum SORT { POPULAR, HIGHRATE; }
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_API_KEY = BuildConfig.MOVIE_API_KEY;

    private static final String MOVIE_URL =
            "http://api.themoviedb.org/3/movie/";

    private static final String MOVIE_POPULAR_URL = "popular";

    private static final String MOVIE_HIGHEST_RATED_URL = "top_rated";

    private static final String MOVIE_POSTER_URL = "http://image.tmdb.org/t/p/";

    private static final String MOVIE_POSTER_SIZE_SMALL = "w185/";

    private static final String MOVIE_POSTER_SIZE_BIG = "original/";

    final static String API_KEY = "api_key";

    /**
     * Builds the URL used to talk to the movie server using an api key.
     *
     * @return The URL to use to query the weather server.
     */
    public static URL buildMoviesUrl(SORT sort) {
        String strMovieUri = MOVIE_URL;

        if(sort == SORT.POPULAR){
            strMovieUri += MOVIE_POPULAR_URL;
        } else if (sort == SORT.HIGHRATE){
            strMovieUri += MOVIE_HIGHEST_RATED_URL;
        }

        Uri builtUri = Uri.parse(strMovieUri).buildUpon()
                .appendQueryParameter(API_KEY, MOVIE_API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static URL buildMoviePosterUrl(String posterPath, SIZE size){

        String strMoviePosterUri = MOVIE_POSTER_URL;

        if(size == SIZE.SMALL) {
            strMoviePosterUri += MOVIE_POSTER_SIZE_SMALL;
        } else if (size == SIZE.BIG){
            strMoviePosterUri += MOVIE_POSTER_SIZE_BIG;
        }

        Uri builtUri = Uri.parse(strMoviePosterUri + posterPath).buildUpon().build();;

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
