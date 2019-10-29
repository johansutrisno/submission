package com.dicoding.submission.home.favorite.tvshow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.dicoding.submission.databinding.FragmentTvShowFavoriteBinding;
import com.dicoding.submission.home.tvshow.TvShowNavigator;
import com.dicoding.submission.model.TvShowsData;

import java.util.ArrayList;
import java.util.List;

public class TvShowFavoriteFragment extends Fragment implements TvShowNavigator {

    private TvShowFavoriteAdapter mAdapter;
    private List<TvShowsData> mListTvShow;
    private FragmentTvShowFavoriteBinding binding;

    public TvShowFavoriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show_favorite, container, false);
        View view = binding.getRoot();

        mListTvShow = new ArrayList<>();

        TvShowFavoriteViewModel mFavoriteViewModel = new TvShowFavoriteViewModel(Injection.provideDataRepository(requireContext()));
        mFavoriteViewModel.setMovieNavigator(this);
        mFavoriteViewModel.getListMovieFavorite();

        binding.setMViewModel(mFavoriteViewModel);
        binding.setIsLoading(true);
        binding.setIsEmpty(false);

        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        mAdapter = new TvShowFavoriteAdapter(mListTvShow);
        RecyclerView mRecyclerViewFavorite = binding.recyclerView;
        mRecyclerViewFavorite.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerViewFavorite.setAdapter(mAdapter);
    }


    @Override
    public void loadListTvShow(List<TvShowsData> dataList) {
        mListTvShow.addAll(dataList);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                binding.setIsLoading(false);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void errorLoadListTvShow(String message) {
        binding.setIsLoading(false);
        binding.setIsEmpty(true);
    }
}
