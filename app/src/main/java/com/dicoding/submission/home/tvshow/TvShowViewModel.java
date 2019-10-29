package com.dicoding.submission.home.tvshow;

import com.dicoding.submission.data.DataRepository;
import com.dicoding.submission.data.DataSource;
import com.dicoding.submission.model.TvShow;

public class TvShowViewModel {
    private DataRepository dataRepository;
    private TvShowNavigator tvShowNavigator;

    public TvShowViewModel(DataRepository repository) {
        dataRepository = repository;
    }

    public void setTvShowNavigator(TvShowNavigator navigator) {
        tvShowNavigator = navigator;
    }

    public void getListTvShow() {
        dataRepository.getListTvShows(new DataSource.GetTvShowsCallback() {
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
