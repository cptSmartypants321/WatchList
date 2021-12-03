package com.example.watchlist.DAO;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOMovieList {
    private DatabaseReference databaseReference;
    public DAOMovieList(){
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(MovieList.class.getSimpleName());
    }
    public Task<Void> add(MovieList movList)
    {
           return databaseReference.push().setValue(movList);
    }
}
