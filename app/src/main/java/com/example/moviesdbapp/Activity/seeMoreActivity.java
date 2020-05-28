package com.example.moviesdbapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesdbapp.Adapter.HomeAdapter;
import com.example.moviesdbapp.Adapter.seeMoreScreenAdapter;
import com.example.moviesdbapp.Model.Movie;
import com.example.moviesdbapp.R;
import com.example.moviesdbapp.RecyclerViewClickListner;
import com.example.moviesdbapp.ViewModel.ViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.moviesdbapp.Adapter.HomeAdapter.ImageBaseUrl;

public class seeMoreActivity extends AppCompatActivity implements RecyclerViewClickListner
{
    private RecyclerView SeeMoreRecycerView;
    public static String TYPE="";
    public ArrayList<Movie> movieArrayList;
    private RecyclerView.Adapter adapter;
    private String tag;
    private Toolbar toolbar;
    private Movie movie;
    private ImageView favourite;
    private Button trailer;
    Context context;
    FirebaseAuth mauth;
    FirebaseUser currentuser;
    String user;
    ViewModel viewModel;
    Boolean isFav=false;
    BottomSheetDialog bottomSheetDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        toolbar=findViewById(R.id.toolbar);
        mauth=FirebaseAuth.getInstance();
        currentuser=mauth.getCurrentUser();
        user=currentuser.getUid();

        Bundle bundle=getIntent().getExtras();

        assert bundle != null;
        try {
            movieArrayList =(ArrayList<Movie>) bundle.getSerializable("movies");
            tag=bundle.getString("TAG");
            TYPE=bundle.getString("type");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        toolbar.setTitle(tag);
        SeeMoreRecycerView=findViewById(R.id.allMoviesRecyclerView);
        SeeMoreRecycerView.setLayoutManager(new GridLayoutManager(this,3));
        SeeMoreRecycerView.setHasFixedSize(true);
        adapter=new seeMoreScreenAdapter(movieArrayList,this,this);
        SeeMoreRecycerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(int position)
    {
       showBottomsheet(position);
        trailer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(seeMoreActivity.this,DetailsActivity.class);
                String id= movie.getId();
                String movie_image=movie.getMovieImage();
                if (!id.equals(""))
                {
                    intent.putExtra("id",id);
                    intent.putExtra("image",movie_image);
                    intent.putExtra("type",TYPE);
                    intent.putExtra("moviename",movie.getOriginalTitle());
                    startActivity(intent);
                   // bottomSheetDialog.dismiss();
                }
            }
        });
        AddToFavourites(user,movie);

        Toast.makeText(seeMoreActivity.this,"clciked"+position,Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("SetTextI18n")
    public void showBottomsheet(int position)
    {
         bottomSheetDialog=new BottomSheetDialog(seeMoreActivity.this,R.style.BottomSheetDialogTheme);
        View bottomShetView= LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.bottom_sheet_layout, findViewById(R.id.bottomSheetContainer));
        ImageView movieImag=bottomShetView.findViewById(R.id.movieImage);
        TextView MovieName=bottomShetView.findViewById(R.id.MovieName);
        TextView MovieYear=bottomShetView.findViewById(R.id.ReleaseDate);
        TextView Movieoverview=bottomShetView.findViewById(R.id.overview);
        favourite=bottomShetView.findViewById(R.id.addtoFavourite);
        trailer=bottomShetView.findViewById(R.id.trailer);
        RatingBar ratingBar=bottomShetView.findViewById(R.id.ratingbar);
        movie=movieArrayList.get(position);

        checkForFavourtie(movie);

        float rating=Float.parseFloat(movie.getUserRating());
        rating=rating/2;
        ratingBar.setRating(rating);

        Picasso.get().load(ImageBaseUrl+movie.getMovieImage()).into(movieImag);
        MovieName.setText("Name: "+movie.getOriginalTitle());
        MovieYear.setText("Year: "+movie.getRelaseDate());
        Movieoverview.setText("OverView"+movie.getOverView());
        bottomSheetDialog.setContentView(bottomShetView);
        bottomSheetDialog.show();
    }
    public void AddToFavourites(String user,Movie movie)
    {
        favourite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                add(user,movie);
            }
        });
    }
    public void add(String user,Movie movie)
    {
        viewModel= ViewModelProviders.of(this).get(ViewModel.class);
        if (isFav)
        {
            viewModel.setQueryMutable2(movie.getId());
            viewModel.getMessageOfRemoved().observe(this, new Observer<String>()
            {
                @Override
                public void onChanged(String s)
                {
                    Toast.makeText(getApplicationContext(),"Removed from Favourite"+s,Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            viewModel.setUser(user);
            viewModel.setQuery(movie);
            viewModel.getMessageOfAdd().observe(this, new Observer<String>()
            {
                @Override
                public void onChanged(String s)
                {
                    if (s.equals("sucess"))
                    {
                        Toast.makeText(getApplicationContext(),"Added to Favourite",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Not Added to Favourite",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
    public void checkForFavourtie(Movie movie)
    {
        viewModel= ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.setQueryMutable2(movie.getId());
        viewModel.isFav().observe(this, new Observer<Boolean>()
        {
            @Override
            public void onChanged(Boolean aBoolean)
            {
                setFavorite(aBoolean);
            }
        });
    }
    private void setFavorite(Boolean fav)
    {
        if (fav)
        {
            isFav = true;
            favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            isFav = false;
            favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }
}
