package com.example.joaco.labsmobiles;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Joaco on 4/3/2018.
 */

public class SavePreferences {
    private static SavePreferences savePreferences;
    private SharedPreferences sharedPreferences;

    public static SavePreferences getInstance(Context context){
        if (savePreferences==null){
            savePreferences = new SavePreferences(context);
        }
        return savePreferences;
    }

    private SavePreferences(Context context){
        sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public String getData(String key){
        if (sharedPreferences != null){
            return sharedPreferences.getString(key, "");
        }
        return " ";
    }

    public void deleteData(){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }
}
