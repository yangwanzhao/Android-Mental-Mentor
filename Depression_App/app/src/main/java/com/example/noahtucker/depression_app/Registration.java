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
 * Created by noahtucker on 4/24/17.
 */

public class Registration extends AsyncTask<String, Void, String> {
    String u;
    String p;
    String full_name;
    String area;
    String level;
    MainActivity m;

    public Registration(String username, String password, String full_name,String area, String level, MainActivity main){
        this.u = username;
        this.p = password;
        this.full_name = full_name;
        this.area = area;
        this.level = level;
        this.m = main;
    }
    @Override
    protected String doInBackground(String...params){
        try {
            String method = "register";

            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(u, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(p, "UTF-8");
            data += "&" + URLEncoder.encode("full_name", "UTF-8") + "=" + URLEncoder.encode(full_name, "UTF-8");
            data += "&" + URLEncoder.encode("areas_of_stress", "UTF-8") + "=" + URLEncoder.encode(area, "UTF-8");
            data += "&" + URLEncoder.encode("stress_level", "UTF-8") + "=" + URLEncoder.encode(level, "UTF-8");

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
        m.regResult = s;
        this.m.success1(s);

    }

}