package com.example.android.popularmovies.Utilities;

import android.content.ContentValues;
import android.content.Context;

import com.example.android.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public final class OpenMovieJsonUtils {
    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing popular movies.
     *
     * @param movieJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Movie[] getMovieDataFromJson(Context context, String movieJsonStr)
            throws JSONException {


        final String OWM_RESULTS = "results";
        final String OWM_TITLE = "title";
        final String OWM_POSTER_PATH = "poster_path";
        final String OWM_OVERVIEW = "overview";
        final String OWM_VOTE_AVERAGE = "vote_average";
        final String OWM_RELEASE_DATE = "release_date";

        Movie[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray movieArray = movieJson.getJSONArray(OWM_RESULTS);

        parsedMovieData = new Movie[movieArray.length()];

        /*long localDate = System.currentTimeMillis();
        long utcDate = SunshineDateUtils.getUTCDateFromLocal(localDate);
        long startDay = SunshineDateUtils.normalizeDate(utcDate);*/

        for (int i = 0; i < movieArray.length(); i++) {

            /* Get the JSON object representing the day */
            JSONObject movie = movieArray.getJSONObject(i);

            String title = "";
            String posterUrl = "";
            String plotDescription = "";
            String userRating = "";
            String releaseDate = "";

            if(movie.has(OWM_TITLE)){
                title = movie.getString(OWM_TITLE);
            }

            if(movie.has(OWM_POSTER_PATH)){
                posterUrl = movie.getString(OWM_POSTER_PATH);
            }

            if(movie.has(OWM_OVERVIEW)){
                plotDescription = movie.getString(OWM_OVERVIEW);
            }

            if(movie.has(OWM_VOTE_AVERAGE)){
                userRating = movie.getString(OWM_VOTE_AVERAGE);
            }

            if(movie.has(OWM_RELEASE_DATE)){
                releaseDate = movie.getString(OWM_RELEASE_DATE);
            }


            parsedMovieData[i] = new Movie(title, posterUrl, plotDescription, userRating, releaseDate);
        }

        return parsedMovieData;
    }
}
