package com.dicoding.submission.home.favorite.movie;

import com.dicoding.submission.data.DataRepository;
import com.dicoding.submission.data.DataSource;
import com.dicoding.submission.home.movie.MovieNavigator;
import com.dicoding.submission.model.Movie;

public class MovieFavoriteViewModel {
    private DataRepository dataRepository;
    private MovieNavigator movieNavigator;

    public MovieFavoriteViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void setMovieNavigator(MovieNavigator navigator) {
        movieNavigator = navigator;
    }

    public void getListMovieFavorite() {
        dataRepository.getFavoriteMovie(new DataSource.GetMoviesCallback() {
            @Override
            public void onMovieLoaded(Movie movie) {
                movieNavigator.loadListMovie(movie.getResults());
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                movieNavigator.errorLoadListMovie(errorMessage);
            }
        });
    }
}
