package com.example.moviesdbapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesdbapp.Model.Movie;
import com.example.moviesdbapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>
{
    private ArrayList<Movie> movieArrayList;
    private Context context;
    public static final String ImageBaseUrl="https://image.tmdb.org/t/p/w500";

    public HomeAdapter( ArrayList<Movie> movieArrayList,Context context)
    {
        this.movieArrayList=movieArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_single_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position)
    {
        Movie movie=movieArrayList.get(position);
        Picasso.get().load(ImageBaseUrl+movie.getMovieImage()).into(holder.movieImage);
        holder.moviename.setText(movie.getOriginalTitle());
        if (!movie.getRelaseDate().equals(""))
        {
            String date=movie.getRelaseDate();
            String year1="";
            char[] year=date.toCharArray();
            for (int i=0;i<=3;i++)
            {
                year1=year1+year[i];
            }

            holder.movieyear.setText(year1);
        }
        else
        {
            holder.movieyear.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount()
    {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView movieImage;
       private TextView moviename,movieyear;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            movieImage=itemView.findViewById(R.id.movieImage);
            moviename=itemView.findViewById(R.id.movieName);
            movieyear=itemView.findViewById(R.id.movieYear);
        }
    }
}
