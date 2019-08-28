package com.dicoding.submission.data.remote;

import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.TvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ApiInterface {

    @GET("discover/movie")
    Call<Movie> getAllMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShow> getAllTvShows(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<Movie> getSearchMovie(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("search/tv")
    Call<TvShow> getSearchTvShow(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("discover/movie")
    Call<Movie> getNewRelease(@Query("api_key") String apiKey, @Query("primary_release_date.gte") String gteDate,
                              @Query("primary_release_date.lte") String lteDate);
}
