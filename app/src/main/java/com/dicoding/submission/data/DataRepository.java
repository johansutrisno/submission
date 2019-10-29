package com.dicoding.submission.data;

import com.dicoding.submission.data.local.LocalDataSource;
import com.dicoding.submission.data.remote.RemoteDataSource;
import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShow;
import com.dicoding.submission.model.TvShowsData;

import java.util.List;

public class DataRepository implements DataSource {

    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;

    public DataRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public void getListMovies(final GetMoviesCallback callback) {
        localDataSource.getListMovies(new GetMoviesCallback() {
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

    @Override
    public void getFavoriteMovie(final GetMoviesCallback callback) {
        localDataSource.getFavoriteMovie(new GetMoviesCallback() {
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

    @Override
    public void saveDataMovie(List<Result> movie) {

    }

    @Override
    public void saveFavoriteMovie(int id, boolean isFavorite) {

    }

    @Override
    public void getListTvShows(final GetTvShowsCallback callback) {
        localDataSource.getListTvShows(new GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                callback.onTvShowLoaded(tvShow);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                getTvShowsFromRemoteDataSource(callback);
            }
        });
    }

    @Override
    public void getFavoriteTvShow(final GetTvShowsCallback callback) {
        localDataSource.getFavoriteTvShow(new GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                callback.onTvShowLoaded(tvShow);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    @Override
    public void saveDataTvShow(List<TvShowsData> data) {

    }

    @Override
    public void saveFavoriteTvShow(int id, boolean isFavorite) {

    }

    @Override
    public void getListSearchMovie(String query, final GetMoviesCallback callback) {
        remoteDataSource.getListSearchMovie(query, new GetMoviesCallback() {
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

    @Override
    public void getListSearchTvShow(String query, final GetTvShowsCallback callback) {
        remoteDataSource.getListSearchTvShow(query, new GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                callback.onTvShowLoaded(tvShow);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    @Override
    public void getNewRelease(String date, final GetMoviesCallback callback) {
        remoteDataSource.getNewRelease(date, new GetMoviesCallback() {
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

    @Override
    public void saveDailyReminderState(Boolean state) {
        localDataSource.saveDailyReminderState(state);
    }

    @Override
    public Boolean getDailyReminderState() {
        return localDataSource.getDailyReminderState();
    }

    @Override
    public void saveReleaseReminderState(Boolean state) {
        localDataSource.saveReleaseReminderState(state);
    }

    @Override
    public Boolean getReleaseReminderState() {
        return localDataSource.getReleaseReminderState();
    }

    private void getMoviesFromRemoteDataSource(final GetMoviesCallback callback) {
        remoteDataSource.getListMovies(new GetMoviesCallback() {
            @Override
            public void onMovieLoaded(Movie movie) {
                localDataSource.saveDataMovie(movie.getResults());
                callback.onMovieLoaded(movie);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    private void getTvShowsFromRemoteDataSource(final GetTvShowsCallback callback) {
        remoteDataSource.getListTvShows(new GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                localDataSource.saveDataTvShow(tvShow.getDataList());
                callback.onTvShowLoaded(tvShow);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });

    }
}
