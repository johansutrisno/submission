package com.dicoding.submission.home.favorite.movie;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.submission.Injection;
import com.dicoding.submission.R;
import com.dicoding.submission.databinding.FragmentMovieFavoriteBinding;
import com.dicoding.submission.home.movie.MovieNavigator;
import com.dicoding.submission.model.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieFavoriteFragment extends Fragment implements MovieNavigator {

    private MovieFavoriteAdapter mAdapter;
    private List<Result> mListMovie;
    private FragmentMovieFavoriteBinding binding;

    public MovieFavoriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_favorite, container, false);
        View view = binding.getRoot();

        mListMovie = new ArrayList<>();

        MovieFavoriteViewModel mFavoriteViewModel = new MovieFavoriteViewModel(Injection.provideMovieRepository(requireContext()));
        mFavoriteViewModel.setMovieNavigator(this);
        mFavoriteViewModel.getListMovieFavorite();

        binding.setMViewModel(mFavoriteViewModel);
        binding.setIsLoading(true);
        binding.setIsEmpty(false);

        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        mAdapter = new MovieFavoriteAdapter(mListMovie);
        RecyclerView mRecyclerViewFavorite = binding.recyclerView;
        mRecyclerViewFavorite.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerViewFavorite.setAdapter(mAdapter);
    }

    @Override
    public void loadListMovie(List<Result> movieList) {
        mListMovie.addAll(movieList);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                binding.setIsLoading(false);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void errorLoadListMovie(String message) {
        binding.setIsLoading(false);
        binding.setIsEmpty(true);
    }
}
