package com.dicoding.submission.data.remote;

import com.dicoding.submission.Helper;
import com.dicoding.submission.data.DataSource;
import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShow;
import com.dicoding.submission.model.TvShowsData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getListMovies(final GetMoviesCallback callback) {
        Call<Movie> call = apiInterface.getAllMovies(Helper.Const.API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                callback.onMovieLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }

    @Override
    public void getFavoriteMovie(GetMoviesCallback callback) {

    }

    @Override
    public void saveDataMovie(List<Result> movie) {

    }

    @Override
    public void saveFavoriteMovie(int id, boolean isFavorite) {

    }

    @Override
    public void getListTvShows(final GetTvShowsCallback callback) {
        Call<TvShow> call = apiInterface.getAllTvShows(Helper.Const.API_KEY);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                callback.onTvShowLoaded(response.body());
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }

    @Override
    public void getFavoriteTvShow(GetTvShowsCallback callback) {

    }

    @Override
    public void saveDataTvShow(List<TvShowsData> data) {

    }

    @Override
    public void saveFavoriteTvShow(int id, boolean isFavorite) {

    }

    @Override
    public void getListSearchMovie(String query, final GetMoviesCallback callback) {
        Call<Movie> call = apiInterface.getSearchMovie(Helper.Const.API_KEY, query);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                callback.onMovieLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }

    @Override
    public void getListSearchTvShow(String query, final GetTvShowsCallback callback) {
        Call<TvShow> call = apiInterface.getSearchTvShow(Helper.Const.API_KEY, query);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                callback.onTvShowLoaded(response.body());
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }

    @Override
    public void getNewRelease(String date, final GetMoviesCallback callback) {
        Call<Movie> call = apiInterface.getNewRelease(Helper.Const.API_KEY, date, date);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                callback.onMovieLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }

    @Override
    public void saveDailyReminderState(Boolean state) {

    }

    @Override
    public Boolean getDailyReminderState() {
        return null;
    }

    @Override
    public void saveReleaseReminderState(Boolean state) {

    }

    @Override
    public Boolean getReleaseReminderState() {
        return null;
    }
}
