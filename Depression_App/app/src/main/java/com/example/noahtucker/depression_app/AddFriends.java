package com.example.noahtucker.depression_app;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
/**
 * Created by noahtucker on 4/25/17.
 */

public class AddFriends extends AsyncTask<String, Void, String>{
        String id;
        String friend;
        MainActivity m;

    public AddFriends(String id, String friend, MainActivity m){
        this.id = id;
        this.friend = friend;
        this.m = m;

    }


    @Override
    protected String doInBackground(String...params){
        try {
            String method = "add_friends";

            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            data += "&" + URLEncoder.encode("friend", "UTF-8") + "=" + URLEncoder.encode(friend, "UTF-8");

            Data app = new Data();
            String link = app.getLink();
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            //Read server response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            Log.d("My Result:", sb.toString());

            return sb.toString();
        }catch(Exception e){
            System.out.println("My Result:" + e.getMessage() );

            return new String("Exception: " + e.getMessage());
        }
    }
    @Override
    public void onPostExecute(String s){
        if(s.startsWith("_")){
            //m.makeAddFriendToast(friend);
            Toast.makeText(m, friend + " added to your friends.", Toast.LENGTH_SHORT).show();

        }
        else if(s.startsWith("You")){
            //m.makeYourselfToast(s);
            Toast.makeText(m, s, Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(m, friend + " is already your friend.", Toast.LENGTH_SHORT).show();

        }
    }
}
