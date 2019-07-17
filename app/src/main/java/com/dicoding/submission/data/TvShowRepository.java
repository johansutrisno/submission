package com.dicoding.submission.data;

import com.dicoding.submission.data.local.TvShowLocalDataSource;
import com.dicoding.submission.data.remote.TvShowRemoteDataSource;
import com.dicoding.submission.model.TvShow;

public class TvShowRepository implements TvShowDataSource {

    private TvShowRemoteDataSource tvShowRemoteDataSource;
    private TvShowLocalDataSource tvShowLocalDataSource;

    public TvShowRepository(TvShowRemoteDataSource tvShowRemoteDataSource, TvShowLocalDataSource tvShowLocalDataSource) {
        this.tvShowRemoteDataSource = tvShowRemoteDataSource;
        this.tvShowLocalDataSource = tvShowLocalDataSource;
    }

    @Override
    public void getListTvShows(final GetTvShowsCallback callback) {
        tvShowLocalDataSource.getListTvShows(new GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                callback.onTvShowLoaded(tvShow);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                getTvShowsFromRemoteDataSource(callback);
            }
        });
    }

    public void getListFavoriteTvShow(final GetTvShowsCallback callback) {
        tvShowLocalDataSource.getFavoriteTvShow(new GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                callback.onTvShowLoaded(tvShow);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    private void getTvShowsFromRemoteDataSource(final GetTvShowsCallback callback) {

        tvShowRemoteDataSource.getListTvShows(new GetTvShowsCallback() {
            @Override
            public void onTvShowLoaded(TvShow tvShow) {
                tvShowLocalDataSource.saveDataTvShow(tvShow.getDataList());
                callback.onTvShowLoaded(tvShow);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }
}
