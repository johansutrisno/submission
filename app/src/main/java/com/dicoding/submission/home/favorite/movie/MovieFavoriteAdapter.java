package com.dicoding.submission.home.favorite.movie;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.dicoding.submission.R;
import com.dicoding.submission.databinding.ItemMovieBinding;
import com.dicoding.submission.model.Result;

import java.util.List;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.ViewHolder> {

    private List<Result> list;
    private LayoutInflater layoutInflater;

    public MovieFavoriteAdapter(List<Result> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemMovieBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemMovieBinding binding;

        ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
