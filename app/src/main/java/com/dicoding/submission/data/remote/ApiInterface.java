package com.dicoding.submission.data.remote;

import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.TvShow;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("https://api.themoviedb.org/3/discover/movie?api_key=fc7cace3c43d3bf03694157f2cd0cb7f&language=en-US")
    Call<Movie> getAllMovies();

    @GET("https://api.themoviedb.org/3/discover/tv?api_key=fc7cace3c43d3bf03694157f2cd0cb7f&language=en-US")
    Call<TvShow> getAllTvShows();
}
