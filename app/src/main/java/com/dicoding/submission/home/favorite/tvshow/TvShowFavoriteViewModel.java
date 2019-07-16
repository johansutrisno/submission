package com.dicoding.submission.home.favorite.tvshow;

import com.dicoding.submission.data.TvShowDataSource;
import com.dicoding.submission.data.TvShowRepository;
import com.dicoding.submission.home.tvshow.TvShowNavigator;
import com.dicoding.submission.model.TvShow;

public class TvShowFavoriteViewModel {
    private TvShowRepository tvShowRepository;
    private TvShowNavigator tvShowNavigator;

    public TvShowFavoriteViewModel(TvShowRepository movieRepository) {
        this.tvShowRepository = movieRepository;
    }

    public void setMovieNavigator(TvShowNavigator navigator) {
        tvShowNavigator = navigator;
    }

    public void getListMovieFavorite() {
        tvShowRepository.getListFavoriteTvShow(new TvShowDataSource.GetTvShowsCallback() {
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
