package com.dicoding.submission.home.movie;

import com.dicoding.submission.data.MovieDataSource;
import com.dicoding.submission.data.MovieRepository;
import com.dicoding.submission.model.Movie;

public class MovieViewModel {
    private MovieRepository movieRepository;
    private MovieNavigator movieNavigator;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void setMovieNavigator(MovieNavigator navigator) {
        movieNavigator = navigator;
    }

    public void getListMovie() {
        movieRepository.getListMovies(new MovieDataSource.GetMoviesCallback() {
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
