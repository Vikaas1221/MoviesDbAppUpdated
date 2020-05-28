package com.example.moviesdbapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.moviesdbapp.Activity.MainActivity;
import com.example.moviesdbapp.Activity.splash_screen;
import com.example.moviesdbapp.Model.Movie;
import com.example.moviesdbapp.R;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class mtplayerwidget extends AppWidgetProvider
{
    public static ArrayList<Movie> upcomingMovies=new ArrayList<>();
    public static String title;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId)
    {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.mtplayerwidget);
        views.setTextViewText(R.id.MovieTitleWidget,title);

        Intent intent = new Intent(context, RecipeWidgetService.class);
        views.setRemoteAdapter(R.id.MovieListViewWidget, intent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.hasExtra("moviename")) {
          //  Movie movie = intent.getParcelableExtra("movie");
            title=intent.getExtras().getString("moviename");
        } else {
            title = "No Recipe Selected";
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        ComponentName myWidget = new ComponentName(context.getApplicationContext(), mtplayerwidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(myWidget);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.MovieListViewWidget);
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }
}

