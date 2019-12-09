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
 * Created by noahtucker on 5/1/17.
 */

public class GetPostedActivity extends AsyncTask<String, Void, String> {
    String username;
    MainActivity m;

    public GetPostedActivity(String username, MainActivity m) {
        this.username = username;
        this.m = m;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String method = "get_act";
            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

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
        } catch (Exception e) {
            System.out.println("My Result:" + e.getMessage());

            return new String("Exception: " + e.getMessage());
        }
    }
    @Override
    public void onPostExecute(String s){

        if(m.act[0] == ""){
            m.actCount =0;
        }
        //creates array of posts
        // post < id ;
        int postTemp =0;
        for(int j=0; j<s.length();j++) {
            if (s.charAt(j) == '<') {
                postTemp++;
            }
            else if(s.charAt(j) == '>'){
                m.actCount++;
            }
            if(postTemp == m.actCount && s.charAt(j) != '>') {
                m.act[m.actCount] += s.charAt(j);
            }
            else if(postTemp > m.actCount && s.charAt(j) != '<'){
                m.id2[m.actCount]+= s.charAt(j);
            }

        }
        for(int ij=0; ij < m.act.length; ij++ ){
            if(m.act[ij] != "") {
                m.act[ij] += "\n\nPosted by: " + m.id2[ij];

            }

        }
        System.out.println("here");
        m.postAct();


    }
}
