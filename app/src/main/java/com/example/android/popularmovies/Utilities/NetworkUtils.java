package com.example.android.popularmovies.Utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_API_KEY = "7ac81052647f10c184bd1f0b96117960";

    private static final String POPULAR_MOVIE_URL =
            "http://api.themoviedb.org/3/movie/popular";

    private static final String MOVIE_POSTER_URL = "http://image.tmdb.org/t/p/";

    private static final String MOVIE_POSTER_SIZE = "w185/";

    private static final String MOVIE_BASE_URL = POPULAR_MOVIE_URL;

    final static String API_KEY = "api_key";

    /**
     * Builds the URL used to talk to the movie server using an api key.
     *
     * @return The URL to use to query the weather server.
     */
    public static URL buildPopularMoviesUrl() {
        Uri builtUri = Uri.parse(POPULAR_MOVIE_URL).buildUpon()
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

    public static URL buildMoviePosterUrl(String posterPath){
        Uri builtUri = Uri.parse(MOVIE_POSTER_URL + MOVIE_POSTER_SIZE + posterPath).buildUpon().build();

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
