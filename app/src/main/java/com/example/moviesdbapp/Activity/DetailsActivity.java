package com.example.moviesdbapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.moviesdbapp.Fragments.exoPlayerFragment;
import com.example.moviesdbapp.R;
import com.example.moviesdbapp.id_interface;

public class DetailsActivity extends AppCompatActivity
{
    public static String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String id=getIntent().getExtras().getString("id");
        String img=getIntent().getExtras().getString("image");
      //  String name=getIntent().getExtras().getString("moviename");
        type=getIntent().getExtras().getString("type");
        exoPlayerFragment fragment=new exoPlayerFragment();
        fragment.setId(id);
        fragment.setImage(img);
        fragment.setType(type);
      //  fragment.setMovieNAme(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.detailsContainer,fragment).addToBackStack(null).commit();


    }
}
