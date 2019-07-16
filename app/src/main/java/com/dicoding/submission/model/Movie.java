package com.dicoding.submission.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("results")
    private List<Result> results;

    public Movie(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }
}
