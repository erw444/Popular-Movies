package com.example.android.popularmovies;

public class Movie {
    private String mTitle;
    private String mPosterPath;
    private String mPlotDescription;
    private String mUserRating;
    private String mReleaseDate;

    public Movie(String title, String posterPath, String plotDescription, String userRating, String releaseDate){
        mTitle = title;
        mPosterPath = posterPath;
        mPlotDescription = plotDescription;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getPlotDescription() {
        return mPlotDescription;
    }

    public String getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public void setPlotDescription(String mPlotDescription) {
        this.mPlotDescription = mPlotDescription;
    }

    public void setUserRating(String mUserRating) {
        this.mUserRating = mUserRating;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
}
