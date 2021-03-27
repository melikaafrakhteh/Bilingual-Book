package com.afrakhteh.book.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceSingle {

    private static SharedPreferenceSingle INSTANCE;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPreferenceSingle(Context context){
        sharedPreferences = context.getSharedPreferences(Constants.SHARED,0);
        editor = sharedPreferences.edit();

    }
    public static SharedPreferenceSingle getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new SharedPreferenceSingle(context);
        }
        return INSTANCE;
    }

    //theme
    public void setTheme(boolean isDark){
        editor.putBoolean(Constants.Color,isDark);
        editor.apply();
    }
    public Boolean getTheme(){
        return sharedPreferences.getBoolean(Constants.Color,false);
    }
}
