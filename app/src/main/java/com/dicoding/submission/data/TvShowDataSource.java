package com.dicoding.submission.data;

import com.dicoding.submission.model.TvShow;

public interface TvShowDataSource {

    void getListTvShows(GetTvShowsCallback callback);

    interface GetTvShowsCallback {
        void onTvShowLoaded(TvShow tvShow);
        void onDataNotAvailable(String errorMessage);
    }
}
