package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mTitle);
        out.writeString(mPosterPath);
        out.writeString(mPlotDescription);
        out.writeString(mUserRating);
        out.writeString(mReleaseDate);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Movie(Parcel in) {
        mTitle = in.readString();
        mPosterPath = in.readString();
        mPlotDescription = in.readString();
        mUserRating = in.readString();
        mReleaseDate = in.readString();
    }
}
