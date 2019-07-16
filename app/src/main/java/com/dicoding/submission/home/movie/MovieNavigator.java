package com.dicoding.submission.home.movie;

import com.dicoding.submission.model.Result;

import java.util.List;

public interface MovieNavigator {
    void loadListMovie(List<Result> movieList);
    void errorLoadListMovie(String message);
}
