package com.dp.patidatsamajdirectoryapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by ps11 on 04/07/17.
 */

public class SharedPrefUtil {
    Context mContext;

    public SharedPrefUtil(Context mContext) {
        this.mContext = mContext;
    }

    public void addString(String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("APOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public void addStringSet(String key, Set value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("APOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key,value);
        editor.commit();
    }

    public void addInteger(String key, Integer value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("APOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public String retrieveString(String key, String defValue){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("APOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString(key,defValue);
    }

    public int retrieveInt(String key, int defValue){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("APOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getInt(key,defValue);
    }

    public Set retrieveStringSet(String key, Set defValue){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("APOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getStringSet(key,defValue);
    }




}
