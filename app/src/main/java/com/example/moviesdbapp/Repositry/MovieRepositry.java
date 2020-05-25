package com.example.moviesdbapp.Repositry;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviesdbapp.Model.Movie;
import com.example.moviesdbapp.Model.ReviewsModel;
import com.example.moviesdbapp.Model.Trailers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.example.moviesdbapp.Activity.MainActivity.context;

import java.sql.DataTruncation;
import java.util.ArrayList;

public class MovieRepositry
{
    String API_KEY="";
    String type="";
    String id="";
    public static  int i=0;
    private static final String MOVIETYPE="movies";
    private static  final String TVTYPE="tv";
    private static MovieRepositry instance;
    public static MovieRepositry getInstance()
    {
        if (instance==null)
        {
            instance=new MovieRepositry();
        }
        return instance;
    }
    public void getType(String type)
    {
        this.type=type;
    }
    public void getId(String id)
    {
        this.id=id;
    }
    public MutableLiveData<ArrayList<Movie>> getAllPopularMovies()
    {
        API_KEY="https://api.themoviedb.org/3/"+type+"/popular?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US&page=1";
        Log.d("qwert","In repo"+i++);
        final MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();
        final ArrayList<Movie> arrayList=new ArrayList<>();
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, API_KEY, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            { ;
            Log.d("callingjsonapi","calling json api");
                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String original_name= null;
                    if (type.equals("movie"))
                    {
                        try {
                            original_name = object.getString("original_title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            original_name = object.getString("original_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    String image_url= null;
                    try {
                        image_url = object.getString("poster_path");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String user_Rating= null;
                    try {
                        user_Rating = object.getString("vote_average");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Relase_date= "";
                    try {
                        Relase_date = object.getString("release_date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String overview= null;
                    try {
                        overview = object.getString("overview");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id= null;
                    try {
                        id = object.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Movie model=new Movie(original_name,image_url,user_Rating,Relase_date,overview,id);
                    model.setType(type);
                 //   Log.d("poiy",""+query+"(("+model.getOriginalTitle());
                    arrayList.add(model);
                 //   Log.d("added","added sucess"+query);

                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<Movie>> getAllTopRatedMovies()
    {

        API_KEY="https://api.themoviedb.org/3/"+type+"/top_rated?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US&page=1";
        Log.d("qwert","In repo");
        final MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();
        final ArrayList<Movie> arrayList=new ArrayList<>();
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
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String original_name= null;
                    if (type.equals("movie"))
                    {
                        try {
                            original_name = object.getString("original_title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            original_name = object.getString("original_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String image_url= null;
                    try {
                        image_url = object.getString("poster_path");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String user_Rating= null;
                    try {
                        user_Rating = object.getString("vote_average");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Relase_date= "";
                    try {
                        Relase_date = object.getString("release_date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String overview= null;
                    try {
                        overview = object.getString("overview");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id= null;
                    try {
                        id = object.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Movie model=new Movie(original_name,image_url,user_Rating,Relase_date,overview,id);
                    model.setType(type);
                    arrayList.add(model);


                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<Movie>> getAllUpcomingMovies()
    {

        API_KEY="https://api.themoviedb.org/3/"+type+"/upcoming?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US&page=1";
        Log.d("qwert","In repo");
        final MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();
        final ArrayList<Movie> arrayList=new ArrayList<>();
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
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String original_name= null;
                    if (type.equals("movie"))
                    {
                        try {
                            original_name = object.getString("original_title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            original_name = object.getString("original_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String image_url= null;
                    try {
                        image_url = object.getString("poster_path");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String user_Rating= null;
                    try {
                        user_Rating = object.getString("vote_average");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Relase_date= "";
                    try {
                        Relase_date = object.getString("release_date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String overview= null;
                    try {
                        overview = object.getString("overview");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id= null;
                    try {
                        id = object.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Movie model=new Movie(original_name,image_url,user_Rating,Relase_date,overview,id);
                    model.setType(type);
                    arrayList.add(model);


                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<Movie>> getAllNowPlayingMovies()
    {

        API_KEY="https://api.themoviedb.org/3/"+type+"/now_playing?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US&page=1";
        Log.d("qwert","In repo");
        final MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();
        final ArrayList<Movie> arrayList=new ArrayList<>();
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
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String original_name= null;
                    if (type.equals("movie"))
                    {
                        try {
                            original_name = object.getString("original_title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            original_name = object.getString("original_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String image_url= null;
                    try {
                        image_url = object.getString("poster_path");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String user_Rating= null;
                    try {
                        user_Rating = object.getString("vote_average");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Relase_date= "";
                    try {
                        Relase_date = object.getString("release_date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String overview= null;
                    try {
                        overview = object.getString("overview");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id= null;
                    try {
                        id = object.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Movie model=new Movie(original_name,image_url,user_Rating,Relase_date,overview,id);
                    model.setType(type);
                    arrayList.add(model);


                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<Movie>> getAllairingTodayMovies()
    {

        API_KEY="https://api.themoviedb.org/3/"+type+"/airing_today?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US&page=1";
        Log.d("qwert","In repo");
        final MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();
        final ArrayList<Movie> arrayList=new ArrayList<>();
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
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String original_name= null;
                    if (type.equals("movie"))
                    {
                        try {
                            original_name = object.getString("original_title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            original_name = object.getString("original_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String image_url= null;
                    try {
                        image_url = object.getString("poster_path");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String user_Rating= null;
                    try {
                        user_Rating = object.getString("vote_average");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Relase_date= "";
                    String overview= null;
                    try {
                        overview = object.getString("overview");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id= null;
                    try {
                        id = object.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Movie model=new Movie(original_name,image_url,user_Rating,Relase_date,overview,id);
                    model.setType(type);
                    arrayList.add(model);


                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<Movie>> getAllOnTvTodayMovies()
    {

        API_KEY="https://api.themoviedb.org/3/"+type+"/on_the_air?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US&page=1";
        Log.d("qwert","In repo");
        final MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();
        final ArrayList<Movie> arrayList=new ArrayList<>();
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
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String original_name= null;
                    if (type.equals("movie"))
                    {
                        try {
                            original_name = object.getString("original_title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            original_name = object.getString("original_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String image_url= null;
                    try {
                        image_url = object.getString("poster_path");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String user_Rating= null;
                    try {
                        user_Rating = object.getString("vote_average");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Relase_date= "";
                    String overview= null;
                    try {
                        overview = object.getString("overview");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id= null;
                    try {
                        id = object.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Movie model=new Movie(original_name,image_url,user_Rating,Relase_date,overview,id);
                    model.setType(type);
                    arrayList.add(model);


                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }

    public MutableLiveData<ArrayList<Trailers>> getAllTrailers(String id)
    {
        final String TrailersAPIKEY="https://api.themoviedb.org/3/"+type+"/"+id+"/videos?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US";
        final MutableLiveData<ArrayList<Trailers>> data = new MutableLiveData<>();
        final ArrayList<Trailers> arrayList=new ArrayList<>();
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, TrailersAPIKEY, null, new Response.Listener<JSONObject>()
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
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String key= null;
                    try {
                        key = object.getString("key");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Trailers model=new Trailers(key);
                    arrayList.add(model);
                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<ReviewsModel>> getAllReviews(String id)
    {
        final String TrailersAPIKEY="https://api.themoviedb.org/3/"+type+"/"+id+"/reviews?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US";
        final MutableLiveData<ArrayList<ReviewsModel>> data = new MutableLiveData<>();
        final ArrayList<ReviewsModel> arrayList=new ArrayList<>();
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, TrailersAPIKEY, null, new Response.Listener<JSONObject>()
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
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Author= null;
                    try {
                        Author = object.getString("author");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Content= null;
                    try {
                        Content = object.getString("content");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ReviewsModel model=new ReviewsModel(Author,Content);
                    arrayList.add(model);
                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }





}
