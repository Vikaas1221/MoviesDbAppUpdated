package com.example.moviesdbapp.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.moviesdbapp.Model.Movie;

import com.example.moviesdbapp.Repositry.MovieRepositry;

import java.io.IOException;
import java.util.ArrayList;

public class ViewModel extends androidx.lifecycle.ViewModel
{
    String quer="";
    MovieRepositry movieRepo=null;
    MutableLiveData<String> queryMutable=new MutableLiveData<>();
    LiveData<ArrayList<Movie>> popularlistLiveData;
    LiveData<ArrayList<Movie>> topRatedlistLiveData;
    LiveData<ArrayList<Movie>> upcominglistLiveData;
    LiveData<ArrayList<Movie>> nowPlayinglistLiveData;
    LiveData<ArrayList<Movie>> allAiringlistLiveData;
    LiveData<ArrayList<Movie>> allOnTvlistLiveData;
    public ViewModel()
    {
        movieRepo=new MovieRepositry();
    }
    public void setType(String type)
    {
        movieRepo.getType(type);
    }
    public LiveData<ArrayList<Movie>> getPopularMutableLiveData()
    {
        Log.d("uytr","int viewmodel");
        if (popularlistLiveData==null)
        {
            popularlistLiveData=movieRepo.getAllPopularMovies();
        }
        return popularlistLiveData;
    }
    public LiveData<ArrayList<Movie>> getTopRatedMutableLiveData()
    {
        Log.d("uytr","int viewmodel");
        if (topRatedlistLiveData==null)
        {
            topRatedlistLiveData=movieRepo.getAllTopRatedMovies();
        }
        return topRatedlistLiveData;
    }
    public LiveData<ArrayList<Movie>> getUpcomingMutableLiveData()
    {
        Log.d("uytr","int viewmodel");
        if (upcominglistLiveData==null)
        {
            upcominglistLiveData=movieRepo.getAllUpcomingMovies();
        }
        return upcominglistLiveData;
    }
    public LiveData<ArrayList<Movie>> getNowPlayingMutableLiveData()
    {
        Log.d("uytr","int viewmodel");
        if (nowPlayinglistLiveData==null)
        {
            nowPlayinglistLiveData=movieRepo.getAllNowPlayingMovies();
        }
        return nowPlayinglistLiveData;
    }
    public LiveData<ArrayList<Movie>> getAllAiringMutableLiveData()
    {
        Log.d("uytr","int viewmodel");
        if (allAiringlistLiveData==null)
        {
            allAiringlistLiveData=movieRepo.getAllairingTodayMovies();
        }
        return allAiringlistLiveData;
    }
    public LiveData<ArrayList<Movie>> getAllOnTvMutableLiveData()
    {
        Log.d("uytr","int viewmodel");
        if (allOnTvlistLiveData==null)
        {
            allOnTvlistLiveData=movieRepo.getAllOnTvTodayMovies();
        }
        return allOnTvlistLiveData;
    }


}
