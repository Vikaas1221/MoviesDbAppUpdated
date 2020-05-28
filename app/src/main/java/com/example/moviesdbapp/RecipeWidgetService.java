package com.example.moviesdbapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.moviesdbapp.Model.Movie;

import java.util.ArrayList;

class RecipeWidgetService extends RemoteViewsService
{
    private ArrayList<Movie> MovieArrayList;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent)
    {
        return new RemoteListViewFactory(getApplicationContext());
    }
    class RemoteListViewFactory implements RecipeWidgetService.RemoteViewsFactory {

        final Context context;

        public RemoteListViewFactory(Context context) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged()
        {
            MovieArrayList = mtplayerwidget.upcomingMovies;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (MovieArrayList == null)
                return 0;
            return MovieArrayList.size();
        }

        @Override
        public RemoteViews getViewAt(int index) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.movie_widget_layout);
            remoteViews.setTextViewText(R.id.movieItemTextView, MovieArrayList.get(index).getOriginalTitle()
            );
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
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
