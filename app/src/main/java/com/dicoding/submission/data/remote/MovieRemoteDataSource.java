package com.dicoding.submission.data.remote;

import com.dicoding.submission.data.MovieDataSource;
import com.dicoding.submission.data.remote.ApiClient;
import com.dicoding.submission.data.remote.ApiInterface;
import com.dicoding.submission.model.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource implements MovieDataSource {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getListMovies(final GetMoviesCallback callback) {

        Call<Movie> call = apiInterface.getAllMovies();
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
}
