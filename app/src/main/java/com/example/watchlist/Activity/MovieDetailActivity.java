package com.example.watchlist.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.watchlist.util.Constants;
import com.example.watchlist.DAO.DAOMovieList;
import com.example.watchlist.DAO.MovieList;
import com.example.watchlist.Model.Movie;

import com.example.watchlist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.squareup.picasso.Picasso;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieYear;
    private TextView director;
    private TextView actors;
    private TextView category;
    private TextView rating;
    private TextView writers;
    private TextView plot;
    private TextView boxOffice;
    private TextView runtime;

    private RequestQueue queue;
    private String movieID;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_det);
        movie=(Movie)getIntent().getSerializableExtra("movie");
        movieID=movie.getID();
        setUp();
        getMovieDetailsTest(movieID);
        queue= Volley.newRequestQueue(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToWatchList();
            }


        });

    }

    public void addToWatchList(){
//        alertDialogBuilder=new AlertDialog.Builder(this);
//        View view=getLayoutInflater().inflate(R.layout.dialog_view,null);
//        EditText newSearch=(EditText)view.findViewById(R.id.search_field);
//        Button submitButton=(Button) view.findViewById(R.id.submit_btn);
//
//        alertDialogBuilder.setView(view);
//        dialog=alertDialogBuilder.create();
//        dialog.show();
//
//        submitButton.setOnClickListener(v -> {
//            Pref pref=new Pref(MainActivity.this);
//            if(!newSearch.getText().toString().isEmpty()){
//                String search=newSearch.getText().toString();
//                pref.setSearch(search);
//                movieLIst.clear();
//                getMovies(search);
//                movieRecyclerViewAdapter.notifyDataSetChanged();
//            }
//            dialog.dismiss();
//        });
        DAOMovieList dao=new DAOMovieList();
        MovieList movieList=new MovieList(movieID);
        dao.add(movieList).addOnSuccessListener(suc->
        {
            Toast.makeText(this,"Record is inserted: " + movieID,Toast.LENGTH_SHORT).show();
            Log.d( "addToWatchList_______: ",movieID);
        }).addOnFailureListener(er->{
            Toast.makeText(this,"" + er.getMessage(),Toast.LENGTH_SHORT).show();
        });


    }
    private void getMovieDetailsTest(String movieID) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.omdbapi.com/?apikey=4b416f1b&i="+movieID)
                .method("GET", null)
                .build();

        Movie movie=new Movie();

                try  {
                    okhttp3.Response response = client.newCall(request).execute();
                    String jsonData=response.body().string();
                  //  JSONObject Jobject=null;
                    if (jsonData ==null || jsonData=="")
                    {
                        Log.d("HAHAHAHAHAH ","HELP MEPLESSSSSSSSSSSS");
                    }

                      //  String temp=Jobject.toString();
                        Log.d( "run: ",jsonData);
                        Object obj = new JSONParser().parse(jsonData);
                        JSONObject jo=(JSONObject) obj;
                        movie.setTitle((String)jo.get("Title"));
                        Log.d( "run: ",movie.getTitle());
                        movie.setRelease("Released: "+(String)jo.get("Released"));
                        movie.setDirector("Director: "+(String)jo.get("Director"));
                        movie.setWriter("Writers: "+(String)jo.get("Writers"));
                        movie.setPlot("Plot: "+(String)jo.get("Plot"));
                        movie.setRuntime("Runtime: "+(String)jo.get("Runtime"));
                        movie.setActors("Actors: "+(String)jo.get("Actors"));
                        movie.setPoster((String)jo.get("Poster"));
                    movie.setBoxOffice("Box Office"+(String)jo.get("BoxOffice"));

                        movieTitle.setText(movie.getTitle());
                    movieTitle.setText(movie.getTitle());
                    movieYear.setText(movie.getRelease());
                    director.setText(movie.getDirector());
                    writers.setText(movie.getWriter());
                    plot.setText(movie.getPlot());
                    runtime.setText(movie.getRuntime());
                    actors.setText(movie.getActors());
                    boxOffice.setText(movie.getBoxOffice());
                    Picasso.get().load(movie.getPoster())
                            .placeholder(android.R.drawable.ic_btn_speak_now)
                            .into(movieImage);





                } catch (Exception e) {
                    e.printStackTrace();
                }





    }

//    private void getMovieDetails(String movieID) {
//        String url = Constants.URL_ID+movieID;
//        Log.d("getMovieDetails: ","AI DANO AMA NA DALI " + movieID);
//        JsonObjectRequest req = new JsonObjectRequest(url, null,
//                response -> {
//                    try {
//                        Log.i( "getMovieDetails: ",response.get("Runtime").toString());
//                        if(response.has("Ratings")) {
//                            Log.d( "getMovieDetails: ","AIIII RABOTI");
//                            JSONArray ratings = response.getJSONArray("Ratings");
//                            String source = null;
//                            String value = null;
//                            if (ratings.length() > 0) {
//                                JSONObject mRatings = ratings.getJSONObject(ratings.length() - 1);
//                                source = mRatings.getString("Source");
//                                value = mRatings.getString("Value");
//                                rating.setText(source +" : "+ value);
//                            } else {
//                                rating.setText("Ratings:N/A");
//                            }
//
//
//                            // fields
//                            movieTitle.setText(response.getString("Title"));
//                            movieYear.setText("Released: "+response.getString("Released"));
//                            director.setText("Director: "+response.getString("Director"));
//                            writers.setText("Writers: "+response.getString("Writers"));
//                            plot.setText("Plot: "+response.getString("Plot"));
//                            runtime.setText("Runtime: "+response.getString("Runtime"));
//                            actors.setText("Actors: "+response.getString("Actors"));
//
//                            Picasso.get().load(response.getString("Poster"))
//                                    .placeholder(android.R.drawable.ic_btn_speak_now)
//                                    .into(movieImage);
//                            boxOffice.setText("Box Office"+response.getString("BoxOffice"));
//                        }
//                    }catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("Error: ", error.getMessage());
//            }
//
//        });
//        queue.add(req);
//
//    }

    private void setUp() {
        movieTitle=(TextView) findViewById(R.id.movieTitleDet);
        movieImage=(ImageView) findViewById(R.id.movieImageIdDet);
        movieYear=(TextView) findViewById(R.id.movieReleaseDet);
        director=(TextView) findViewById(R.id.directedByDet);
        category=(TextView) findViewById(R.id.movieCatDet);
        rating=(TextView) findViewById(R.id.movieRatingDet);
        writers=(TextView) findViewById(R.id.writersDet);
        plot=(TextView) findViewById(R.id.plotDet);
        boxOffice=(TextView) findViewById(R.id.boxOfficeDet);
        runtime=(TextView) findViewById(R.id.runtimeDet);
        actors=(TextView) findViewById(R.id.actorsDet);
    }
////    public void searchForImdbID(View view) {
////     //   viewModel.searchForImdbID(editText.getText().toString());
////    }
////!TODO Fix me pls
//
//    private void getMovieDetails(String id) {
//        String page= Constants.URL_ID;
//        String url= Constants.URL_LEFT+ id;
//        Log.d("halpppppp",id);
//        JsonObjectRequest req = new JsonObjectRequest(url , null,
//                response -> {
//
//                    try {
//                        Log.d( "halp part1 : ",response.getString("Ratings"));

//                           else{
//                               rating.setText("Rating: N/A");
//                           }
//                           movieTitle.setText(response.getString("Title"));
//                           movieYear.setText("Released: " +response.getString("Released"));
//                           director.setText("Director: " +response.getString("Director"));
//                           writers.setText("Writers: " +response.getString("Writer"));
//                           plot.setText("Plot Summary: " +response.getString("Plot"));
//                           runtime.setText("Runtime: " +response.getString("Runtime"));
//                           actors.setText("Actors: " +response.getString("Actors"));
//                           boxOffice.setText("Box Office: "+response.getString("BoxOffice"));
//                           Picasso.get().load(response.getString("Poster"))
//                                   .placeholder(android.R.drawable.ic_btn_speak_now)
//                                   .into(movieImage);
//
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("Error: ", error.getMessage());
//            }
//        });
//        queue.add(req);
//    }
//

}