package com.example.desarrollocometaapp.Classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.desarrollocometaapp.R;

public class SharedPreferenceConfig {private SharedPreferences sharedPreferences;
    private Context context;


    public SharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_shared_preference), Context.MODE_PRIVATE);
    }

    public void loginStatus(boolean status){
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putBoolean("LOGIN", status);
        editor.commit();
    }

    public boolean readStatus(){
        boolean status = false;
        status = sharedPreferences.getBoolean("LOGIN", false);
        return status;
    }

    public void saveIdAndToken(String id, String token){
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("ID", id);
        editor.putString("TOKEN", token);
        editor.commit();
    }

    public String getId(){
        return sharedPreferences.getString("ID", "");
    }

    public String getToken(){
        return sharedPreferences.getString("TOKEN", "");
    }

}
