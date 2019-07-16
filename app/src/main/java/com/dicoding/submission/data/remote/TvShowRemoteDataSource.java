package com.dicoding.submission.data.remote;

import com.dicoding.submission.data.TvShowDataSource;
import com.dicoding.submission.model.TvShow;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRemoteDataSource implements TvShowDataSource {
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


    @Override
    public void getListTvShows(final GetTvShowsCallback callback) {

        Call<TvShow> call = apiInterface.getAllTvShows();
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
}
