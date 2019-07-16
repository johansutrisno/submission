package com.dicoding.submission;

import android.content.Context;

import com.dicoding.submission.data.MovieRepository;
import com.dicoding.submission.data.TvShowRepository;
import com.dicoding.submission.data.local.MovieLocalDataSource;
import com.dicoding.submission.data.local.TvShowLocalDataSource;
import com.dicoding.submission.data.remote.MovieRemoteDataSource;
import com.dicoding.submission.data.remote.TvShowRemoteDataSource;

public class Injection {
    public static MovieRepository provideMovieRepository(Context context) {
        return new MovieRepository(new MovieRemoteDataSource(), new MovieLocalDataSource(context));
    }

    public static TvShowRepository provideTvShowRepository(Context context) {
        return new TvShowRepository(new TvShowRemoteDataSource(), new TvShowLocalDataSource(context));
    }
}
