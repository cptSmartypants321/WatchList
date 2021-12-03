package com.example.watchlist.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.watchlist.Model.Movie;
import com.example.watchlist.R;

import java.util.ArrayList;
import java.util.List;

public class MovieCardFragment<LayoutManagerType> extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager laoyoutMnager;
    private List<Movie> movieLIst;
    private RequestQueue queue;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieLIst=new ArrayList<>();

        // Initialize dataset;

    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
////        rootView.setTag("MovieCardFragment");
////
//    }
}
