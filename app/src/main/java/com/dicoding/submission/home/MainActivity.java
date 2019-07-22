package com.dicoding.submission.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dicoding.submission.R;
import com.dicoding.submission.home.favorite.FavoriteFragment;
import com.dicoding.submission.home.movie.MovieFragment;
import com.dicoding.submission.home.tvshow.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;

    private static final String FRAGMENT_HOME = "fragment_home";
    private static final String FRAGMENT_OTHER = "fragment_other";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewFragment(new MovieFragment(), FRAGMENT_HOME);
                    return true;
                case R.id.navigation_dashboard:
                    viewFragment(new TvShowFragment(), FRAGMENT_OTHER);
                    return true;
                case R.id.navigation_notifications:
                    viewFragment(new FavoriteFragment(), FRAGMENT_OTHER);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_home);
        }

    }

    private void viewFragment(Fragment fragment, String name) {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.container_layout, fragment);

        final int count = fragmentManager.getBackStackEntryCount();

        if (name.equals(FRAGMENT_OTHER)) fragmentTransaction.addToBackStack(name);

        fragmentTransaction.commit();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentManager.getBackStackEntryCount() <= count) {

                    fragmentManager.popBackStack(FRAGMENT_OTHER, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    fragmentManager.removeOnBackStackChangedListener(this);

                    navigation.getMenu().getItem(0).setChecked(true);
                }
            }
        });
    }

}
