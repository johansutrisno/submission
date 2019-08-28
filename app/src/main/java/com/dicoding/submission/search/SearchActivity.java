package com.dicoding.submission.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dicoding.submission.Injection;
import com.dicoding.submission.R;
import com.dicoding.submission.databinding.ActivitySearchBinding;
import com.dicoding.submission.home.movie.MovieListAdapter;
import com.dicoding.submission.home.movie.MovieNavigator;
import com.dicoding.submission.home.tvshow.TvShowListAdapter;
import com.dicoding.submission.home.tvshow.TvShowNavigator;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShowsData;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements MovieNavigator, TvShowNavigator {

    public static final String MOVIE_SEARCH = "movie_search";
    public static final String TV_SHOW_SEARCH = "tv_show_search";

    private SearchViewModel searchViewModel;

    private MovieListAdapter mMovieListAdapter;
    private TvShowListAdapter mTvShowListAdapter;

    private List<Result> mListMovie;
    private List<TvShowsData> mListTvShow;

    private ActivitySearchBinding binding;

    private String mSearchMode = "";
    private String mQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setLifecycleOwner(this);

        mListMovie = new ArrayList<>();
        mListTvShow = new ArrayList<>();

        searchViewModel = new SearchViewModel(Injection.provideDataRepository(this));

        if (getIntent().getStringExtra(MOVIE_SEARCH) != null) {
            initMovie();
        } else if (getIntent().getStringExtra(TV_SHOW_SEARCH) != null){
            initTvShow();
        }

        binding.setMViewModel(searchViewModel);

        SearchView searchView = binding.searchView;

        searchView.setQuery(mQuery, false);
        searchView.setIconified(false);
        searchView.clearFocus();

        InputMethodManager in = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (mSearchMode.equals(MOVIE_SEARCH)) {
                    searchMovie(s);
                } else if (mSearchMode.equals(TV_SHOW_SEARCH)) {
                    searchTvShow(s);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    private void initMovie() {
        searchViewModel.setMovieNavigator(this);
        mSearchMode = MOVIE_SEARCH;
        mQuery = getIntent().getStringExtra(MOVIE_SEARCH);
        searchMovie(mQuery);
    }

    private void initTvShow() {
        searchViewModel.setTvShowNavigator(this);
        mSearchMode = TV_SHOW_SEARCH;
        mQuery = getIntent().getStringExtra(TV_SHOW_SEARCH);
        searchTvShow(mQuery);
    }

    private void searchTvShow(String query) {
        binding.setIsLoading(true);
        searchViewModel.getSearchTvShow(query);
        showTvSearch();
    }

    private void searchMovie(String query) {
        binding.setIsLoading(true);
        searchViewModel.getSearchMovie(query);
        showMovieSearch();
    }

    private void showMovieSearch() {
        mListMovie.clear();
        mMovieListAdapter = new MovieListAdapter(mListMovie);
        RecyclerView rvMovie = binding.recyclerView;
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        rvMovie.setAdapter(mMovieListAdapter);
    }

    private void showTvSearch() {
        mListTvShow.clear();
        mTvShowListAdapter = new TvShowListAdapter(mListTvShow);
        RecyclerView rvTvShow = binding.recyclerView;
        rvTvShow.setLayoutManager(new LinearLayoutManager(this));
        rvTvShow.setAdapter(mTvShowListAdapter);
    }

    @Override
    public void loadListMovie(List<Result> movieList) {
        mListMovie.addAll(movieList);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                binding.setIsLoading(false);
                mMovieListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void errorLoadListMovie(String message) {
        Log.e("ERROR", message);
    }

    @Override
    public void loadListTvShow(List<TvShowsData> dataList) {
        mListTvShow.addAll(dataList);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                binding.setIsLoading(false);
                mTvShowListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void errorLoadListTvShow(String message) {
        Log.e("ERROR", message);
    }
}
