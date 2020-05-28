package com.example.moviesdbapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import com.example.moviesdbapp.Model.Movie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

class RemoteListViewFactory implements RecipeWidgetService.RemoteViewsFactory
{

    FirebaseAuth mauth;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    CollectionReference reference=firestore.collection("Favourite");
    ArrayList<Movie> arrayList=new ArrayList<>();
    final Context context;
    Intent intent;



    private void initilize() throws NullPointerException
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
    }
    public RemoteListViewFactory(Context context, Intent intent)
    {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        initilize();
    }

    @Override
    public void onDataSetChanged() {
        initilize();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (arrayList == null)
            return 0;
        return arrayList.size();
    }

    @Override
    public RemoteViews getViewAt(int index) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.movie_widget_layout);
        remoteViews.setTextViewText(R.id.movieItemTextView, arrayList.get(index).getOriginalTitle());
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
