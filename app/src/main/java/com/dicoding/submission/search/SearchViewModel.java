package com.dicoding.submission.search;

import com.dicoding.submission.data.DataRepository;
import com.dicoding.submission.data.DataSource;
import com.dicoding.submission.home.movie.MovieNavigator;
import com.dicoding.submission.home.tvshow.TvShowNavigator;
import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.TvShow;

public class SearchViewModel {
    private DataRepository dataRepository;
    private MovieNavigator movieNavigator;
    private TvShowNavigator tvShowNavigator;

    SearchViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    void setMovieNavigator(MovieNavigator navigator) {
        movieNavigator = navigator;
    }

    void setTvShowNavigator(TvShowNavigator navigator) {
        tvShowNavigator = navigator;
    }

    void getSearchMovie(String query) {
        dataRepository.getListSearchMovie(query, new DataSource.GetMoviesCallback() {
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

    void getSearchTvShow(String query) {
        dataRepository.getListSearchTvShow(query, new DataSource.GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                tvShowNavigator.loadListTvShow(tvShow.getDataList());
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                tvShowNavigator.errorLoadListTvShow(errorMessage);
            }
        });
    }
}
