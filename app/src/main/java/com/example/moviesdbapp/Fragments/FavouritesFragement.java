package com.example.moviesdbapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesdbapp.R;
import com.example.moviesdbapp.ViewModel.ViewModel;

public class FavouritesFragement extends Fragment
{
    private static FavouritesFragement instance;

    public static FavouritesFragement getInstance()
    {
        if (instance==null)
        {
            instance=new FavouritesFragement();
        }
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View  view=inflater.inflate(R.layout.favourites_fragment,container,false);
        return view;
    }
}
