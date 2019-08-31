package com.dicoding.submission.home.movie;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.submission.Injection;
import com.dicoding.submission.databinding.FragmentMovieBinding;
import com.dicoding.submission.R;
import com.dicoding.submission.home.MainActivity;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.notification.SettingsActivity;
import com.dicoding.submission.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        MovieViewModel mMovieViewModel = new MovieViewModel(Injection.provideDataRepository(requireContext()));
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_lang, menu);

        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();

        searchView.setSearchableInfo(Objects.requireNonNull(searchManager)
                .getSearchableInfo(getActivity().getComponentName()));

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(requireContext(), SearchActivity.class);
                intent.putExtra(SearchActivity.MOVIE_SEARCH, s);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        } else if (id == R.id.action_change_settings) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
            return true;
        } else if (id == R.id.action_settings) {
            startActivity(new Intent(requireActivity(), SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
