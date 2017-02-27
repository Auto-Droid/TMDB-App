package com.sourabhkarkal.movieapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sourabhkarkal.movieapp.R;
import com.sourabhkarkal.movieapp.fragment.MovieFragment;

/**
 * Created by sourabhkarkal on 25/02/17.
 */

public class HomeScreen extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        // setting up Home Fragment
        setMainFragment();
    }

    private void setMainFragment() {
        MovieFragment movieFragment = new MovieFragment();
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainfragment, movieFragment, "Home");
        ft.commit();
    }
}
