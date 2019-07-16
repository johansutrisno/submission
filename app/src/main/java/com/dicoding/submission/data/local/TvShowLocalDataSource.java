package com.dicoding.submission.data.local;

import android.content.Context;

import com.dicoding.submission.data.TvShowDataSource;
import com.dicoding.submission.model.TvShow;
import com.dicoding.submission.model.TvShowsData;

import java.util.List;

public class TvShowLocalDataSource implements TvShowDataSource {

    private TvShowDao tvShowDao;

    public TvShowLocalDataSource(Context context) {
        tvShowDao = MovieDatabase.getInstance(context).tvShowDao();
    }

    @Override
    public void getListTvShows(final GetTvShowsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TvShowsData> data = tvShowDao.getTvShow();
                if (data.isEmpty()) callback.onDataNotAvailable("Data kosong!");
                else {
                    TvShow tvShow = new TvShow(data);
                    callback.onTvShowLoaded(tvShow);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void getFavoriteTvShow(final GetTvShowsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TvShowsData> tvShowFavorite = tvShowDao.getFavoriteTvShow();
                if (tvShowFavorite.isEmpty()) callback.onDataNotAvailable("Data kosong!");
                else {
                    TvShow tvShow = new TvShow(tvShowFavorite);
                    callback.onTvShowLoaded(tvShow);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void saveDataTvShow(final List<TvShowsData> data) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tvShowDao.insertTvShow(data);
            }
        };
        new Thread(runnable).start();
    }

    public void saveFavorite(final int id, final boolean isFavorite) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tvShowDao.updateFavorite(id, isFavorite);
            }
        };
        new Thread(runnable).start();
    }

}
