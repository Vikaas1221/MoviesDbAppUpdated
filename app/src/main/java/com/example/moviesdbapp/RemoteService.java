package com.example.moviesdbapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviesdbapp.Model.Movie;
import com.example.moviesdbapp.ViewModel.ViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.SplittableRandom;

import static com.example.moviesdbapp.Activity.MainActivity.context;

class RemoteListViewFactory implements RecipeWidgetService.RemoteViewsFactory
{
    ViewModel viewModel;
    private static  String YOURAPIKEY="";
    FirebaseAuth mauth;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    CollectionReference reference=firestore.collection("Favourite");
    ArrayList<Movie> arrayList=new ArrayList<>();
    final Context context;
    Intent intent;



  /*  private void initilize() throws NullPointerException
    {
        try {

            arrayList.clear();
            mauth = FirebaseAuth.getInstance();
            FirebaseUser user = mauth.getCurrentUser();
            String currentuser = user.getUid();

            reference.whereEqualTo("userid", currentuser)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    String movieName = documentSnapshot.getString("MovieName");
                                    String movieOverview = documentSnapshot.getString("MovieOverview");
                                    String movieRating = documentSnapshot.getString("MovieRating");
                                    String movieRelease = documentSnapshot.getString("MovieDate");
                                    String movieId = documentSnapshot.getString("MovieId");
                                    String movieImage = documentSnapshot.getString("MovieImage");
                                    String type = documentSnapshot.getString("Type");
                                    Movie movie = new Movie(movieName, movieImage, movieRating, movieRelease, movieOverview, movieId);
                                    movie.setType(type);
                                    arrayList.add(movie);
                                }
                            }
                        }
                    });
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
   */ //}
    private void laivz() throws NullPointerException
    {
        String API_KEY="https://api.themoviedb.org/3/movie/upcoming?api_key="+YOURAPIKEY+"&language=en-US&page=1";
        Log.d("qwertappwidget","In repo");
       // final ArrayList<Movie> arrayList=new ArrayList<>();
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, API_KEY, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
             //   arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object = null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String original_name = null;
                    try {
                        original_name = object.getString("original_title");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Movie model=new Movie(original_name);
                    Log.d("qwertappwidget","In repo"+original_name);
                    arrayList.add(model);

                }

                }

            }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("somethignwenrwrongg",""+ error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
    }
    public RemoteListViewFactory(Context context, Intent intent)
    {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        laivz();
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context,mtplayerwidget.class);
        int[]appwidgetIds = manager.getAppWidgetIds(componentName);
        manager.notifyAppWidgetViewDataChanged(appwidgetIds,R.id.MovieListViewWidget);
    }

    @Override
    public void onDataSetChanged()
    {
       laivz();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (arrayList == null)
            return 0;

        Log.d("inqwert",""+arrayList.size());
        return arrayList.size();
    }

    @Override
    public RemoteViews getViewAt(int index) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.movie_widget_layout);
        remoteViews.setTextViewText(R.id.movieItemTextView, arrayList.get(index).getOriginalTitle());
        Log.d("inremoteviews",""+arrayList.get(index).getOriginalTitle());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
