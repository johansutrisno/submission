package com.dicoding.submission.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dicoding.submission.model.TvShowsData;

import java.util.List;

@Dao
public interface TvShowDao {
    @Query("SELECT * FROM tvshow")
    List<TvShowsData> getTvShow();

    @Query("SELECT * FROM tvshow WHERE isFavorite = 1")
    List<TvShowsData> getFavoriteTvShow();

    @Insert
    void insertTvShow(List<TvShowsData> tvShowsData);

    @Query("UPDATE tvshow SET isFavorite = :status WHERE id = :id")
    void updateFavorite(int id, boolean status);
}
