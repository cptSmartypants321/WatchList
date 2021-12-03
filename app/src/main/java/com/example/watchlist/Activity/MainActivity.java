package com.example.watchlist.Activity;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.watchlist.util.Constants;
import com.example.watchlist.DAO.DAOMovieList;
import com.example.watchlist.DAO.MovieList;
import com.example.watchlist.Data.MovieRecyclerViewAdapter;
import com.example.watchlist.Model.Movie;
import com.example.watchlist.util.Pref;
import com.example.watchlist.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {

    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    private List<Movie> movieLIst;
    private RequestQueue queue;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        queue= Volley.newRequestQueue(this);



        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        final LinearLayoutManager    _layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(_layoutManager);
        recyclerView.setHasFixedSize(true);
        if(recyclerView == null)
        {
            Log.d("is it NULLLLLL: ", "YESSSSSSSSSSSSSSSSSSSSSSSSS" );
        }

        testDB();


        Pref pref=new Pref(MainActivity.this);
        String search=pref.getSearch();
        movieLIst=new ArrayList<>();
        movieLIst=getMovies(search);
        //getMovies("");

        //setContentView(R.layout.activity_main);



        movieRecyclerViewAdapter=new MovieRecyclerViewAdapter(this,movieLIst);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        movieRecyclerViewAdapter.notifyDataSetChanged();





        }
        public void testDB(){
            String temp="tt0934706";
            DAOMovieList dao=new DAOMovieList();

            MovieList movieList=new MovieList(temp);
            dao.add(movieList).addOnSuccessListener(suc->
            {
                Toast.makeText(this,"Record is inserted: " + temp,Toast.LENGTH_SHORT).show();
                Log.d( "addToWatchList_______: ",temp);
            }).addOnFailureListener(er->{
                Toast.makeText(this,"" + er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_Search) {
            showInputDialog();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //!TODO sooooo slow
    public void showInputDialog(){
        alertDialogBuilder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.dialog_view,null);
        EditText newSearch=(EditText)view.findViewById(R.id.search_field);
        Button submitButton=(Button) view.findViewById(R.id.submit_btn);

        alertDialogBuilder.setView(view);
        dialog=alertDialogBuilder.create();
        dialog.show();

        submitButton.setOnClickListener(v -> {
            Pref pref=new Pref(MainActivity.this);
            if(!newSearch.getText().toString().isEmpty()){
                String search=newSearch.getText().toString();
                pref.setSearch(search);
                movieLIst.clear();
                getMovies(search);
                movieRecyclerViewAdapter.notifyDataSetChanged();
            }
            dialog.dismiss();
        });

    }
    //get movies
    //url + searchTerm + page

    public List<Movie> getMovies (String searchTerm) {


        String url= Constants.URL_LEFT+ searchTerm+Constants.URL_RIGHT;

        JsonObjectRequest req = new JsonObjectRequest(url , null,
                response -> {
                    try {
                        JSONArray movieArray=response.getJSONArray("Search");
                        for (int i = 0; i <movieArray.length() ; i++) {
                            JSONObject moiveObj=movieArray.getJSONObject(i);
                            Movie movie=new Movie();
                            movie.setTitle(moiveObj.getString("Title"));
                            movie.setYear("Year Released: "+moiveObj.getString("Year"));
                            movie.setType("Type: "+moiveObj.getString("Type"));
                            movie.setPoster(moiveObj.getString("Poster"));
                            movie.setId(moiveObj.getString("imdbID"));
                            Log.d( "Movies:  ",movie.getTitle());
                            movieLIst.add(movie);

                        }
                        movieRecyclerViewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: ", error.getMessage());
            }
        });
        queue.add(req);

        return movieLIst;

    }

}

