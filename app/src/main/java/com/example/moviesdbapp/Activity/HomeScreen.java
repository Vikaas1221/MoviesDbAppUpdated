
package com.example.moviesdbapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.moviesdbapp.Fragments.FavouritesFragement;
import com.example.moviesdbapp.Fragments.MoviesFragment;
import com.example.moviesdbapp.Fragments.TvShowsFragment;
import com.example.moviesdbapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity
{
    public static Context context;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=HomeScreen.this;
        setContentView(R.layout.activity_home_screen);
        navigationView=findViewById(R.id.bottomMenu);
        navigationView.setOnNavigationItemSelectedListener(navItem);
        getSupportFragmentManager().beginTransaction().replace(R.id.HomeScreenContainer,new MoviesFragment()).commit();
    }
    public BottomNavigationView.OnNavigationItemSelectedListener navItem=new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {
            int id=menuItem.getItemId();
            Fragment selectedFragment=null;
            switch (id)
            {
                case R.id.movies:
                {
                    selectedFragment=new
                            MoviesFragment();
                    break;
                }
                case R.id.tvshows:
                {
                    selectedFragment=new TvShowsFragment();
                    break;
                }
                case R.id.favourites:
                {
                    selectedFragment=new FavouritesFragement();
                    break;
                }


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.HomeScreenContainer,selectedFragment).commit();
            return true;
        }
    };
}
