package com.dicoding.submission.data;

import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShow;
import com.dicoding.submission.model.TvShowsData;

import java.util.List;

public interface DataSource {

    void getListMovies(GetMoviesCallback callback);

    void getFavoriteMovie(GetMoviesCallback callback);

    void saveDataMovie(List<Result> movie);

    void saveFavoriteMovie(int id, boolean isFavorite);

    void getListTvShows(GetTvShowsCallback callback);

    void getFavoriteTvShow(GetTvShowsCallback callback);

    void saveDataTvShow(List<TvShowsData> data);

    void saveFavoriteTvShow(int id, boolean isFavorite);

    void getListSearchMovie(String query, GetMoviesCallback callback);

    void getListSearchTvShow(String query, GetTvShowsCallback callback);

    void getNewRelease(String date, GetMoviesCallback callback);

    void saveDailyReminderState(Boolean state);

    Boolean getDailyReminderState();

    void saveReleaseReminderState(Boolean state);

    Boolean getReleaseReminderState();

    interface GetMoviesCallback {
        void onMovieLoaded(Movie movie);
        void onDataNotAvailable(String errorMessage);
    }

    interface GetTvShowsCallback {
        void onTvShowLoaded(TvShow tvShow);
        void onDataNotAvailable(String errorMessage);
    }

}
