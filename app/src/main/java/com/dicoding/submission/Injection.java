package com.dicoding.submission;

import android.content.Context;

import com.dicoding.submission.data.DataRepository;
import com.dicoding.submission.data.local.LocalDataSource;
import com.dicoding.submission.data.remote.RemoteDataSource;

public class Injection {

    public static DataRepository provideDataRepository(Context context) {
        return new DataRepository(new RemoteDataSource(), new LocalDataSource(context));
    }
}
