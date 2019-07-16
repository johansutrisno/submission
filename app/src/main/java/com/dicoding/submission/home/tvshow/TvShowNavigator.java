package com.dicoding.submission.home.tvshow;

import com.dicoding.submission.model.TvShowsData;

import java.util.List;

public interface TvShowNavigator {
    void loadListTvShow(List<TvShowsData> dataList);
    void errorLoadListTvShow(String message);
}
