package com.dicoding.submission.home.favorite.tvshow;

import com.dicoding.submission.data.DataRepository;
import com.dicoding.submission.data.DataSource;
import com.dicoding.submission.home.tvshow.TvShowNavigator;
import com.dicoding.submission.model.TvShow;

public class TvShowFavoriteViewModel {
    private DataRepository dataRepository;
    private TvShowNavigator tvShowNavigator;

    public TvShowFavoriteViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void setMovieNavigator(TvShowNavigator navigator) {
        tvShowNavigator = navigator;
    }

    public void getListMovieFavorite() {
        dataRepository.getFavoriteTvShow(new DataSource.GetTvShowsCallback() {
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
