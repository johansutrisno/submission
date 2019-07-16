package com.dicoding.submission.data.local;

import android.content.Context;

import com.dicoding.submission.data.MovieDataSource;
import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.Result;

import java.util.List;

public class MovieLocalDataSource implements MovieDataSource {

    private MovieDao movieDao;

    public MovieLocalDataSource(Context context) {
        movieDao = MovieDatabase.getInstance(context).movieDao();
    }

    @Override
    public void getListMovies(final GetMoviesCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Result> movie = movieDao.getMovie();
                if (movie.isEmpty()) callback.onDataNotAvailable("Data kosong!");
                else {
                    Movie movies = new Movie(movie);
                    callback.onMovieLoaded(movies);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void getFavoriteMovie(final GetMoviesCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Result> movieFavorite = movieDao.getFavoriteMovie();
                if (movieFavorite.isEmpty()) callback.onDataNotAvailable("Data kosong!");
                else {
                    Movie movie = new Movie(movieFavorite);
                    callback.onMovieLoaded(movie);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void saveDataMovie(final List<Result> movie) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                movieDao.insertMovie(movie);
            }
        };
        new Thread(runnable).start();
    }

    public void saveFavorite(final int id, final boolean isFavorite) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                movieDao.updateFavorite(id, isFavorite);
            }
        };
        new Thread(runnable).start();
    }
}
