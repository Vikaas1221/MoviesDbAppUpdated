package com.example.moviesdbapp.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.moviesdbapp.Adapter.NavigationAdapter;
import com.example.moviesdbapp.Fragments.MoviesFragment;
import com.example.moviesdbapp.Model.Movie;
import com.example.moviesdbapp.Model.NavModel;
import com.example.moviesdbapp.R;
import com.example.moviesdbapp.communicator;
import com.example.moviesdbapp.id_interface;
import com.example.moviesdbapp.mainactivityInterface;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements mainactivityInterface
{
    public static DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    ArrayList<NavModel> arrayList;
    RecyclerView.Adapter adapter;
    public static Toolbar toolbar;
    public static Context context;
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        recyclerView=findViewById(R.id.navigationRecyclerview);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Movies");
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        Fragment fragment=MoviesFragment.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.HomeScreenContainer,fragment).commit();

        setLayoutFroNavigation();


    }
    public void setLayoutFroNavigation()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList=new ArrayList<>();
        String[] naev_ar={"Movies","TvShows","Favourites","Settings","Share"};
        int[] nav_item_images={R.drawable.ic_action_video_library,R.drawable.ic_laptop_chromebook_black_24dp,R.drawable.ic_collections_bookmark_black_24dp,R.drawable.ic_settings_black_24dp,R.drawable.ic_share_black_24dp};
        for (int i=0;i<naev_ar.length;i++)
        {
            NavModel model=new NavModel(naev_ar[i],nav_item_images[i]);
            arrayList.add(model);
        }
        adapter=new NavigationAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void item_id(String id, String type, String image)
    {
        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("image",image);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    @Override
    public void dataArrayList(ArrayList<Movie> movies, String tag, String type)
    {
        Intent intent=new Intent(MainActivity.this,seeMoreActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("movies",(Serializable)movies);
        intent.putExtra("TAG",tag);
        intent.putExtra("type",type);

        intent.putExtras(bundle);
        startActivity(intent);
    }

//    @Override
//    public void dataArrayList(ArrayList<Movie> movies,String tag,String type)
//    {
//        Intent intent=new Intent(MainActivity.this,seeMoreActivity.class);
//        Bundle bundle=new Bundle();
//        bundle.putSerializable("movies",(Serializable)movies);
//        intent.putExtra("TAG",tag);
//        intent.putExtra("type",type);
//
//        intent.putExtras(bundle);
//        startActivity(intent);
////        overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
//    }

}
