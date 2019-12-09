package com.example.noahtucker.depression_app;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
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

public class GetFriends extends AsyncTask<String, Void, String> {
        String id;
        MainActivity m;

    public GetFriends(String id, MainActivity m){
        this.id = id;
        this.m =m;
    }

    @Override
    protected String doInBackground(String...params){
        try {
            String method = "get_friends";

            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

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

        //split friends into an array
        int index=0;
        for(int i =0; i< s.length(); i++){
            if(s.charAt(i) != '_'){
                m.friendship[index] += s.charAt(i);
            }
            else if(s.charAt(i) == '_' && i !=0){
                index++;
            }
        }

        String si = "";

            TextView tv = (TextView) m.findViewById(R.id.textView11);
            for (int yu = 0; yu < m.friendship.length; yu++) {
                si += m.friendship[yu] + " \n";
            }

            tv.setText(si);


    }

}

