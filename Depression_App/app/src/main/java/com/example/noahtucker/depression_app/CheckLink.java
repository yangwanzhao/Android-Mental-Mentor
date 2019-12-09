package com.example.noahtucker.depression_app;

import android.os.AsyncTask;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by noahtucker on 4/26/17.
 */

public class CheckLink extends AsyncTask<String, Void, String> {
        String URLName;
        String check ="";
        String r_or_nr;
        MainActivity m;

    public CheckLink(String URLName, String r_or_nr, MainActivity m){
        this.URLName = URLName;
        this.m = m;
        this.r_or_nr = r_or_nr;
    }

    @Override
    protected String doInBackground(String...params){
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();



            if (con.getResponseCode() != 404){
                check = "true";
                return check;
            }
            else{
                check = "false";

                return check;
            }
        }
        catch (Exception e) {
            e.printStackTrace();

            check = "false";
            return check;
        }
    }
    @Override
    public void onPostExecute(String s){

        if(s == "true"){
            Toast.makeText(m, "Adding link...", Toast.LENGTH_LONG).show();
            AddLink al = new AddLink(URLName,r_or_nr,m);
            al.execute(URLName, r_or_nr);

        }
        else{
            Toast.makeText(m, URLName +" Need to enter a real https://youtube link", Toast.LENGTH_SHORT).show();

        }

    }
}
