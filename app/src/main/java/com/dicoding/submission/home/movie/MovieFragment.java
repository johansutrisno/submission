package com.dicoding.submission.home.movie;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.submission.Injection;
import com.dicoding.submission.databinding.FragmentMovieBinding;
import com.dicoding.submission.R;
import com.dicoding.submission.model.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment implements MovieNavigator {

    private MovieListAdapter adapter;
    private List<Result> listMovie;
    private FragmentMovieBinding binding;

    public MovieFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        View view = binding.getRoot();

        listMovie = new ArrayList<>();

        MovieViewModel mMovieViewModel = new MovieViewModel(Injection.provideMovieRepository(requireContext()));
        mMovieViewModel.setMovieNavigator(this);
        mMovieViewModel.getListMovie();

        binding.setMViewModel(mMovieViewModel);
        binding.setIsLoading(true);
        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        adapter = new MovieListAdapter(listMovie);
        RecyclerView rvMovie = binding.recyclerView;
        rvMovie.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void loadListMovie(List<Result> movieList) {
        listMovie.addAll(movieList);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                binding.setIsLoading(false);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void errorLoadListMovie(String message) {
        Log.e("ERROR", message);
    }
}
