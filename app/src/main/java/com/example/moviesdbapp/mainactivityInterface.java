package com.example.moviesdbapp;

import com.example.moviesdbapp.Model.Movie;

import java.util.ArrayList;

public interface mainactivityInterface
{
    public void item_id(String id,String type,String image);
    public void dataArrayList(ArrayList<Movie> movies, String tag, String type);
    public void favouritesInterface(ArrayList<Movie> movies,int position);
}
