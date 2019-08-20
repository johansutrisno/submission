package com.dicoding.submission.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.dicoding.submission.Injection;
import com.dicoding.submission.R;
import com.dicoding.submission.data.MovieDataSource;
import com.dicoding.submission.data.MovieRepository;
import com.dicoding.submission.data.local.MovieDatabase;
import com.dicoding.submission.home.movie.MovieNavigator;
import com.dicoding.submission.model.Movie;
import com.dicoding.submission.model.Result;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<Result> mWidgetItems = new ArrayList<>();
    private int appWidgetId;
    private final Context mContext;
    private MovieRepository mMovieRepository;
    private MovieNavigator mMovieNavigator;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        mMovieRepository = Injection.provideMovieRepository(context);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
//        mMovieRepository.getListFavoriteMovies(new MovieDataSource.GetMoviesCallback() {
//            @Override
//            public void onMovieLoaded(Movie movie) {
//                mWidgetItems.addAll(movie.getResults());
//            }
//
//            @Override
//            public void onDataNotAvailable(String errorMessage) {
//                mMovieNavigator.errorLoadListMovie(errorMessage);
//            }
//        });

        mWidgetItems = MovieDatabase
                .getInstance(mContext)
                .movieDao()
                .getFavoriteMovie();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        final RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        try {
            Bitmap b = Picasso.get()
                    .load("https://image.tmdb.org/t/p/w185" + mWidgetItems.get(i).getPosterPath())
                    .get();
            rv.setImageViewBitmap(R.id.imageView, b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bundle extras = new Bundle();
        extras.putInt(PosterWidget.EXTRA_ITEM, i);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
