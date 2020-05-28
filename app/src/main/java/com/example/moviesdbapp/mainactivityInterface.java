package com.example.moviesdbapp;

import com.example.moviesdbapp.Model.Movie;

import java.util.ArrayList;

public interface mainactivityInterface
{
    void item_id(String id, String type, String image, String movieName);
    void dataArrayList(ArrayList<Movie> movies, String tag, String type);
    void favouritesInterface(ArrayList<Movie> movies, int position);
}
