package com.dicoding.submission.home.favorite.tvshow;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.submission.R;
import com.dicoding.submission.databinding.ItemTvShowFavoriteBinding;
import com.dicoding.submission.model.TvShowsData;

import java.util.List;

public class TvShowFavoriteAdapter extends RecyclerView.Adapter<TvShowFavoriteAdapter.ViewHolder> {

    private List<TvShowsData> list;
    private LayoutInflater layoutInflater;

    public TvShowFavoriteAdapter(List<TvShowsData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemTvShowFavoriteBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_tv_show_favorite, parent, false);
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

        private final ItemTvShowFavoriteBinding binding;

        ViewHolder(ItemTvShowFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
