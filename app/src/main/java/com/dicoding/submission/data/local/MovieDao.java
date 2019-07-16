package com.dicoding.submission.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dicoding.submission.model.Result;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    List<Result> getMovie();

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    List<Result> getFavoriteMovie();

    @Insert
    void insertMovie(List<Result> movie);

    @Query("UPDATE movie SET isFavorite = :status WHERE id = :id")
    void updateFavorite(int id, boolean status);
}
