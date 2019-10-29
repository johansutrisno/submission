package com.dicoding.submission.home.movie;

import com.dicoding.submission.data.DataRepository;
import com.dicoding.submission.data.DataSource;
import com.dicoding.submission.model.Movie;

public class MovieViewModel {
    private DataRepository dataRepository;
    private MovieNavigator movieNavigator;

    MovieViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    void setMovieNavigator(MovieNavigator navigator) {
        movieNavigator = navigator;
    }

    void getListMovie() {
        dataRepository.getListMovies(new DataSource.GetMoviesCallback() {
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
