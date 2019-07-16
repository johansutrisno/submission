package com.dicoding.submission.home.tvshow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.submission.Injection;
import com.dicoding.submission.R;
import com.dicoding.submission.databinding.FragmentTvShowBinding;
import com.dicoding.submission.model.TvShowsData;

import java.util.ArrayList;
import java.util.List;

public class TvShowFragment extends Fragment implements TvShowNavigator {

    private TvShowListAdapter adapter;
    private List<TvShowsData> list;
    private FragmentTvShowBinding binding;

    public TvShowFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false);
        View view = binding.getRoot();

        list = new ArrayList<>();

        TvShowViewModel tvShowViewModel = new TvShowViewModel(Injection.provideTvShowRepository(requireContext()));
        tvShowViewModel.setTvShowNavigator(this);
        tvShowViewModel.getListTvShow();

        binding.setMViewModel(tvShowViewModel);
        binding.setIsLoading(true);
        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        adapter = new TvShowListAdapter(list);
        RecyclerView rvTvShow = binding.rvTvShow;
        rvTvShow.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvTvShow.setAdapter(adapter);
    }

    @Override
    public void loadListTvShow(List<TvShowsData> dataList) {
        list.addAll(dataList);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                binding.setIsLoading(false);
            }
        });
    }

    @Override
    public void errorLoadListTvShow(String message) {
        Log.e("ERROR", message);
    }
}
