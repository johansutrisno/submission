package com.dicoding.submission.home.favorite.movie;

import com.dicoding.submission.data.MovieDataSource;
import com.dicoding.submission.data.MovieRepository;
import com.dicoding.submission.home.movie.MovieNavigator;
import com.dicoding.submission.model.Movie;

public class MovieFavoriteViewModel {
    private MovieRepository movieRepository;
    private MovieNavigator movieNavigator;

    public MovieFavoriteViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void setMovieNavigator(MovieNavigator navigator) {
        movieNavigator = navigator;
    }

    public void getListMovieFavorite() {
        movieRepository.getListFavoriteMovies(new MovieDataSource.GetMoviesCallback() {
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
