package com.getfreerecharge.trainschedule.webs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dinesh Kumar on 4/29/2016.
 */
public class SharedPreference {

    private final String ADDMOB = "addmob";
     private final String ADDBANNER = "addbanner";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;
    private final String DATABASE_NAME = "PhotoSuit";

    public SharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
    }


    public String getAddmob() {
        return sharedPreferences.getString(ADDMOB, "ca-app-pub-6210235444865574/8140801640");//"ca-app-pub-3223616616608757/3114139820");
    }

    public void setAddmob(String addmob) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(ADDMOB, addmob);
        spEditor.commit();
    }




    public String getAddbanner() {
        return sharedPreferences.getString(ADDBANNER, "ca-app-pub-6210235444865574/6664068443");// "ca-app-pub-3223616616608757/1637406624");
    }

    public void setAddbanner(String addbanner) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(ADDBANNER, addbanner);
        spEditor.commit();
    }

}
