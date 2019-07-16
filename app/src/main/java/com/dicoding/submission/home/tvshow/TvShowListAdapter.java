package com.dicoding.submission.home.tvshow;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.dicoding.submission.R;
import com.dicoding.submission.databinding.ItemTvShowBinding;
import com.dicoding.submission.model.TvShowsData;

import java.util.List;

public class TvShowListAdapter extends RecyclerView.Adapter<TvShowListAdapter.ViewHolder> {

    private List<TvShowsData> list;
    private LayoutInflater layoutInflater;

    public TvShowListAdapter(List<TvShowsData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemTvShowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_tv_show, parent, false);
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

        private final ItemTvShowBinding binding;

        ViewHolder(ItemTvShowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
