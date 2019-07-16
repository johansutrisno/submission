package com.dicoding.submission.home.tvshow;

import com.dicoding.submission.data.TvShowDataSource;
import com.dicoding.submission.data.TvShowRepository;
import com.dicoding.submission.model.TvShow;

public class TvShowViewModel {
    private TvShowRepository tvShowRepository;
    private TvShowNavigator tvShowNavigator;

    public TvShowViewModel(TvShowRepository repository) {
        tvShowRepository = repository;
    }

    public void setTvShowNavigator(TvShowNavigator navigator) {
        tvShowNavigator = navigator;
    }

    public void getListTvShow() {
        tvShowRepository.getListTvShows(new TvShowDataSource.GetTvShowsCallback() {
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
