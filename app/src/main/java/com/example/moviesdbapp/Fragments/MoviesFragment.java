package com.example.moviesdbapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesdbapp.Adapter.HomeAdapter;
import com.example.moviesdbapp.Adapter.NowPlayingAdapter;
import com.example.moviesdbapp.Model.Movie;
import com.example.moviesdbapp.R;
import com.example.moviesdbapp.ViewModel.ViewModel;

import java.util.ArrayList;

public class MoviesFragment extends Fragment
{
    Context context;
    public RecyclerView NowPlayingrecyclerView,TopRatedRecyclerView,PopularRecycerView,UpcomingRecyclerView;

    ViewModel viewModel;
    RecyclerView.Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View  view=inflater.inflate(R.layout.movies_framgemt,container,false);
        NowPlayingrecyclerView=view.findViewById(R.id.nowPlaying);
        TopRatedRecyclerView=view.findViewById(R.id.topRated);
        PopularRecycerView=view.findViewById(R.id.popular);
        UpcomingRecyclerView=view.findViewById(R.id.upcoming);
        context=getContext();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);


        fetchPopularDataFromViewModel(PopularRecycerView);
        fetchDataTopFromViewModel(TopRatedRecyclerView);
       fetchDataNowPlayingFromViewModel(NowPlayingrecyclerView);
        fetchDataUpcomingFromViewModel(UpcomingRecyclerView);


    }
    public void fetchPopularDataFromViewModel(RecyclerView popularRecycerView)
    {

        ArrayList<Movie> movieArrayList=new ArrayList<>();
        viewModel=ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.setType("movie");
        popularRecycerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        popularRecycerView.setHasFixedSize(true);
        viewModel.getPopularMutableLiveData().observe(this, new Observer<ArrayList<Movie>>()
        {
            @Override
            public void onChanged(ArrayList<Movie> movies)
            {
                if (movies.size()>0)
                {
                    movieArrayList.clear();
                    for (int i=0;i<6;i++)
                    {
                        movieArrayList.add(movies.get(i));
                    }
                }
                adapter=new HomeAdapter(movieArrayList,getContext());
                popularRecycerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
       ;
    }
    public void fetchDataTopFromViewModel(RecyclerView topRatedRecyclerView)
    {
        ArrayList<Movie> movieArrayList=new ArrayList<>();
        viewModel=ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.setType("movie");
        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        topRatedRecyclerView.setHasFixedSize(true);
        viewModel.getTopRatedMutableLiveData().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies)
            {

                if (movies.size()>0)
                {
                    movieArrayList.clear();
                    for (int i=0;i<6;i++)
                    {
                        movieArrayList.add(movies.get(i));
                    }
                }

                adapter=new HomeAdapter(movieArrayList,getContext());
                topRatedRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void fetchDataUpcomingFromViewModel(RecyclerView upcomingRecyclerView)
    {
        ArrayList<Movie> movieArrayList=new ArrayList<>();
        viewModel=ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.setType("movie");
        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        upcomingRecyclerView.setHasFixedSize(true);
        viewModel.getUpcomingMutableLiveData().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies)
            {

                if (movies.size()>0)
                {
                    movieArrayList.clear();
                    for (int i=0;i<6;i++)
                    {
                        movieArrayList.add(movies.get(i));
                    }
                }

                adapter=new HomeAdapter(movieArrayList,getContext());
                upcomingRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void fetchDataNowPlayingFromViewModel(RecyclerView nowPlayingrecyclerView)
    {
        ArrayList<Movie> movieArrayList=new ArrayList<>();
        viewModel=ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.setType("movie");
        nowPlayingrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        nowPlayingrecyclerView.setHasFixedSize(true);
        viewModel.getNowPlayingMutableLiveData().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies)
            {

                if (movies.size()>0)
                {
                    movieArrayList.clear();
                    for (int i=0;i<6;i++)
                    {
                        movieArrayList.add(movies.get(i));
                    }
                }
                adapter=new NowPlayingAdapter(movieArrayList,getContext());
                nowPlayingrecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}