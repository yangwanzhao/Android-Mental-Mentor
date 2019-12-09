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

public class UpdateReg extends AsyncTask<String, Void, String>{
        String id;
        String area;
        String level;
        MainActivity m;
    public UpdateReg(String id, String area, String level, MainActivity m){
        this.id =id;
        this.area =area;
        this.level = level;
        this.m =m;
    }

    @Override
    protected String doInBackground(String...params){
        try {
            String method = "extra_reg";

            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            data += "&" + URLEncoder.encode("area", "UTF-8") + "=" + URLEncoder.encode(area, "UTF-8");
            data += "&" + URLEncoder.encode("level", "UTF-8") + "=" + URLEncoder.encode(level, "UTF-8");

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
        if(s.startsWith("Success")){
            m.makeUpdateToast();
        }

    }

}
