package com.example.watchlist.Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchlist.Activity.MovieDetailActivity;
import com.example.watchlist.Model.Movie;
import com.example.watchlist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Movie> movieList;


    public MovieRecyclerViewAdapter(Context context, List<Movie> movies){
        this.context=context;
        movieList=movies;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie=movieList.get(position);
        String posterLink=movie.getPoster();
        Picasso.get().load(posterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.poster);
        holder.title.setText(movie.getTitle());
        holder.type.setText(movie.getType());
        holder.year.setText(movie.getYear());



    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView poster;
        TextView year;
        TextView type;

        public ViewHolder(@NonNull View itemView,Context ctx ) {
            super(itemView);
            context=ctx;

            title=(TextView) itemView.findViewById(R.id.movieTitleId);
            poster=(ImageView) itemView.findViewById(R.id.movieImageId);
            year=(TextView) itemView.findViewById(R.id.movieReleaseID);
            type=(TextView) itemView.findViewById(R.id.movieCatId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Row clicked ","WHOA!!!!!!!!!!!");
                    Movie movie=movieList.get(getAdapterPosition());
                    Intent intent =new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("movie",movie);
                    ctx.startActivity(intent);


                }
            });
        }

        @Override
        public void onClick(View v) {
//    public void searchForImdbID(String imdbID) {
//        MovieApi movieApi = ServiceGenerator.getMovieApi();
//        Call<MovieResponse> call = movieApi.getMovieByImdbID(imdbID);
//        call.enqueue(new Callback<MovieResponse>() {
//            @EverythingIsNonNull
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                if (response.isSuccessful()) {
//                    searchedImdbID.setValue(response.body().getMovieByImbdID());
//                }
//            }
//            @EverythingIsNonNull
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                Log.i("Retrofit", "Something went wrong :(");
//            }
//        });
//    }
        }
    }
}
