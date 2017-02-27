package com.sourabhkarkal.movieapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sourabhkarkal on 24/02/17.
 */

public class ManagePreferences {

    public static final String PAGE_NO ="page_no";
    public static final String PREF ="MovieApp";

    public static void setIntData(Context context,String key,Integer data){
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sharedpreferences.edit().putInt(key, data).apply();
    }

    public static Integer getIntData(Context context,String key){
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedpreferences.getInt(key, 1);
    }


}
