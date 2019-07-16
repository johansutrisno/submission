package com.dicoding.submission.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShowsData;

@Database(entities = {Result.class, TvShowsData.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase INSTANCE;

    public abstract MovieDao movieDao();
    public abstract TvShowDao tvShowDao();

    private static final Object sLock = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, "Movie.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
