package com.example.watchlist.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Pref {
    SharedPreferences sharedPreferences;

    public Pref(Activity activity) {
        sharedPreferences=activity.getPreferences(activity.MODE_PRIVATE);
    }
    public void setSearch(String search){
        sharedPreferences.edit().putString("Search",search).commit();
    }
    public String getSearch(){
        return sharedPreferences.getString("Search","Batman");
    }

}
