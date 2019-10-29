package com.dicoding.submission.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.dicoding.submission.R;
import com.dicoding.submission.data.local.LocalDataSource;
import com.dicoding.submission.databinding.ActivityDetailBinding;
import com.dicoding.submission.model.Result;
import com.dicoding.submission.model.TvShowsData;

public class DetailActivity extends AppCompatActivity {
    private static final int MOVIE = 110;
    private static final int TV_SHOW = 87;

    private int mMode;

    private LocalDataSource mLocalDataSource;

    private Integer mId;
    private boolean isFavorite;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setLifecycleOwner(this);

        DetailModel detailModel = new DetailModel();

        mLocalDataSource = new LocalDataSource(this);

        Result movie = getIntent().getParcelableExtra("movie");
        TvShowsData tvShow = getIntent().getParcelableExtra("tvshow");

        if (movie != null) {
            mMode = MOVIE;
            mId = movie.getId();
            isFavorite = movie.isFavorite();

            detailModel = new DetailModel(movie.getPosterPath(),
                    movie.getTitle(),
                    movie.getReleaseDate(),
                    movie.getVoteAverage(),
                    movie.getOverview());

        } else if (tvShow != null) {
            mMode = TV_SHOW;
            mId = tvShow.getId();
            isFavorite = tvShow.isFavorite();

            detailModel = new DetailModel(tvShow.getPoster_path(),
                    tvShow.getTitle(),
                    "null",
                    tvShow.getVoteAverage(),
                    tvShow.getOverview());
        }

        binding.setDetail(detailModel);
    }

    private void setFavorite() {
        if (isFavorite) mMenu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp));
        else mMenu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorite, menu);
        mMenu = menu;
        setFavorite();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_favorite) {
            isFavorite = !isFavorite;
            setFavorite();
            if (mMode == MOVIE) mLocalDataSource.saveFavoriteMovie(mId, isFavorite);
            else if (mMode == TV_SHOW) mLocalDataSource.saveFavoriteTvShow(mId, isFavorite);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
