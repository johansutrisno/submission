package com.dicoding.submission.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.dicoding.submission.R;
import com.dicoding.submission.data.local.MovieDatabase;
import com.dicoding.submission.model.Result;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<Result> mWidgetItems = new ArrayList<>();
    private final Context mContext;

    public StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
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
