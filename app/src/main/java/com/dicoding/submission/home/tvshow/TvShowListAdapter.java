package com.dicoding.submission.home.tvshow;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.dicoding.submission.R;
import com.dicoding.submission.databinding.ItemTvShowBinding;
import com.dicoding.submission.model.TvShowsData;

import java.util.ArrayList;
import java.util.List;

public class TvShowListAdapter extends RecyclerView.Adapter<TvShowListAdapter.ViewHolder> implements Filterable {

    private List<TvShowsData> list;
    private List<TvShowsData> listFiltered;
    private LayoutInflater layoutInflater;

    public TvShowListAdapter(List<TvShowsData> list) {
        this.list = list;
        this.listFiltered = list;
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
        holder.binding.setData(listFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = list;
                } else {
                    List<TvShowsData> filteredList = new ArrayList<>();
                    for (TvShowsData row : list) {
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (List<TvShowsData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemTvShowBinding binding;

        ViewHolder(ItemTvShowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
