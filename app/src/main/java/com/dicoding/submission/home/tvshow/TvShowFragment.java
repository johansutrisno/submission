package com.dicoding.submission.home.tvshow;

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

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.submission.Injection;
import com.dicoding.submission.R;
import com.dicoding.submission.databinding.FragmentTvShowBinding;
import com.dicoding.submission.model.TvShowsData;
import com.dicoding.submission.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        TvShowViewModel tvShowViewModel = new TvShowViewModel(Injection.provideDataRepository(requireContext()));
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
                intent.putExtra(SearchActivity.TV_SHOW_SEARCH, s);
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
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
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
