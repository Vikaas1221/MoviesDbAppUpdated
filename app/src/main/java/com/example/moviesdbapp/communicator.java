package com.example.moviesdbapp;

import com.example.moviesdbapp.Model.Movie;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public interface communicator
{
    public void dataArrayList(ArrayList<Movie> movies,String tag,String type);
}
