package com.dicoding.submission.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dicoding.submission.Helper;
import com.dicoding.submission.data.DataSource;
import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShow;
import com.dicoding.submission.model.TvShowsData;

import java.util.List;

public class LocalDataSource implements DataSource {

    private MovieDao movieDao;
    private TvShowDao tvShowDao;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LocalDataSource(Context context) {
        movieDao = MovieDatabase.getInstance(context).movieDao();
        tvShowDao = MovieDatabase.getInstance(context).tvShowDao();
        sharedPreferences = context.getSharedPreferences(Helper.Const.SETTINGS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
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

    @Override
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

    @Override
    public void saveDataMovie(final List<Result> movie) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                movieDao.insertMovie(movie);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void saveFavoriteMovie(final int id, final boolean isFavorite) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                movieDao.updateFavorite(id, isFavorite);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void getListTvShows(final GetTvShowsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TvShowsData> data = tvShowDao.getTvShow();
                if (data.isEmpty()) callback.onDataNotAvailable("Data kosong!");
                else {
                    TvShow tvShow = new TvShow(data);
                    callback.onTvShowLoaded(tvShow);
                }
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void getFavoriteTvShow(final GetTvShowsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TvShowsData> tvShowFavorite = tvShowDao.getFavoriteTvShow();
                if (tvShowFavorite.isEmpty()) callback.onDataNotAvailable("Data kosong!");
                else {
                    TvShow tvShow = new TvShow(tvShowFavorite);
                    callback.onTvShowLoaded(tvShow);
                }
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void saveDataTvShow(final List<TvShowsData> data) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tvShowDao.insertTvShow(data);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void saveFavoriteTvShow(final int id, final boolean isFavorite) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tvShowDao.updateFavorite(id, isFavorite);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void getListSearchMovie(String query, GetMoviesCallback callback) {
        // nothing to do
    }

    @Override
    public void getListSearchTvShow(String query, GetTvShowsCallback callback) {
        // nothing to do
    }

    @Override
    public void getNewRelease(String date, GetMoviesCallback callback) {
        // nothing to do
    }

    @Override
    public void saveDailyReminderState(Boolean state) {
        editor.putBoolean(Helper.Const.DAILY_REMINDER, state).apply();
    }

    @Override
    public Boolean getDailyReminderState() {
        return sharedPreferences.getBoolean(Helper.Const.DAILY_REMINDER, false);
    }

    @Override
    public void saveReleaseReminderState(Boolean state) {
        editor.putBoolean(Helper.Const.RELEASE_REMINDER, state).apply();
    }

    @Override
    public Boolean getReleaseReminderState() {
        return sharedPreferences.getBoolean(Helper.Const.RELEASE_REMINDER, false);
    }
}
