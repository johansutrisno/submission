package com.dicoding.submission;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;

import com.dicoding.submission.detail.DetailActivity;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShowsData;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingAdapters {
    @BindingAdapter({"progressBarVisibility"})
    public static void setVisibility(ProgressBar progressBar, Boolean visible) {
        if (visible) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    @BindingAdapter({"posterMovie"})
    public static void loadPoster(ImageView view, String posterUrl) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185" + posterUrl).into(view);
    }

    @BindingAdapter({"onItemClickedTvShow"})
    public static void itemClickedTvShow(final ConstraintLayout constraintLayout, final TvShowsData tvShowsData) {
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = constraintLayout.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("tvshow", tvShowsData);
                context.startActivity(intent);
            }
        });
    }

    @BindingAdapter({"onItemClicked"})
    public static void itemClicked(final ConstraintLayout constraintLayout, final Result result) {
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = constraintLayout.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movie", result);
                context.startActivity(intent);
            }
        });
    }

    @BindingAdapter({"getReleased"})
    public static void getReleasedDate(TextView textView, String date) {
        Log.d("BindingAdapter", date);
        if (!date.equals("null")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            try {
                d = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sdf.applyPattern("dd MMMM yyyy");
            textView.setText("Released on : " + sdf.format(d));
        }
    }

    @BindingAdapter({"getReleasedYear"})
    public static void getReleasedYear(TextView textView, String date) {
        Log.d("BindingAdapter", date);
        if (!date.equals("null")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            try {
                d = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sdf.applyPattern("yyyy");
            textView.setText(sdf.format(d));
        }
    }

}
