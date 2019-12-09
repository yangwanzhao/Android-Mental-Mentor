package com.example.noahtucker.depression_app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by noahtucker on 4/15/17.
 */
public class GetMusic extends AsyncTask<String, Void, String> {
    String type;
    MainActivity m;
    public GetMusic(String type, MainActivity main){
        this.type = type;
        this.m = main;
    }
    @Override
    protected String doInBackground(String...params){
        try {
            String method = "get_music";
            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");

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
        if(m.links[0] == ""){
            m.linkCount =0;
        }
        //creates array of links
        for(int j=0; j<s.length();j++){
            if(s.charAt(j) == '<'){
                m.linkCount++;
            }
            else{
                m.links[m.linkCount] += s.charAt(j);
            }
        }


        m.postLinks();

    }

}