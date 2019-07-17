package com.dicoding.submission.data;

import android.util.Log;

import com.dicoding.submission.data.local.MovieLocalDataSource;
import com.dicoding.submission.data.remote.MovieRemoteDataSource;
import com.dicoding.submission.model.Movie;

public class MovieRepository implements MovieDataSource {

    private MovieRemoteDataSource movieRemoteDataSource;
    private MovieLocalDataSource movieLocalDataSource;

    public MovieRepository(MovieRemoteDataSource movieRemoteDataSource, MovieLocalDataSource movieLocalDataSource) {
        this.movieRemoteDataSource = movieRemoteDataSource;
        this.movieLocalDataSource = movieLocalDataSource;
    }

    @Override
    public void getListMovies(final GetMoviesCallback callback) {
        movieLocalDataSource.getListMovies(new GetMoviesCallback() {
            @Override
            public void onMovieLoaded(Movie movie) {
                callback.onMovieLoaded(movie);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                getMoviesFromRemoteDataSource(callback);
            }
        });
    }

    public void getListFavoriteMovies(final GetMoviesCallback callback) {
        movieLocalDataSource.getFavoriteMovie(new GetMoviesCallback() {
            @Override
            public void onMovieLoaded(Movie movie) {
                callback.onMovieLoaded(movie);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    private void getMoviesFromRemoteDataSource(final GetMoviesCallback callback) {
        movieRemoteDataSource.getListMovies(new GetMoviesCallback() {
            @Override
            public void onMovieLoaded(Movie movie) {
                movieLocalDataSource.saveDataMovie(movie.getResults());
                callback.onMovieLoaded(movie);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }
}
