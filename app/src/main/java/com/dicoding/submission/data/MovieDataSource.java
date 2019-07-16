package com.dicoding.submission.data;

import com.dicoding.submission.model.Movie;

public interface MovieDataSource {

    void getListMovies(GetMoviesCallback callback);

    interface GetMoviesCallback {
        void onMovieLoaded(Movie movie);
        void onDataNotAvailable(String errorMessage);
    }
}
