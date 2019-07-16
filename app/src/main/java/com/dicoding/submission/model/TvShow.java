package com.dicoding.submission.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShow {

    @SerializedName("results")
    private List<TvShowsData> dataList;

    public TvShow(List<TvShowsData> dataList) {
        this.dataList = dataList;
    }

    public List<TvShowsData> getDataList() {
        return dataList;
    }
}