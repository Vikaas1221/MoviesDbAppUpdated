package com.example.moviesdbapp;

import com.example.moviesdbapp.Model.Movie;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public interface communicator
{
    void dataArrayList(ArrayList<Movie> movies, String tag, String type);
}
