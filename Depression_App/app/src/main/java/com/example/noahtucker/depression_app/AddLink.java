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
 * Created by noahtucker on 4/26/17.
 */
public class AddLink extends AsyncTask<String, Void, String> {
    String URLName;
    MainActivity m;
    String r_or_nr;
    public AddLink(String URLName, String r_or_nr, MainActivity m){
        this.URLName = URLName;
        this.m = m;
        this.r_or_nr = r_or_nr;
    }

    @Override
    protected String doInBackground(String...params){
        try {
            String method = "add_music";
            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(URLName, "UTF-8");
            data += "&" + URLEncoder.encode("r_or_nr", "UTF-8") + "=" + URLEncoder.encode(r_or_nr, "UTF-8");

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
    public void onPostExecute(String s) {
        Toast.makeText(m, "Your link has been added.", Toast.LENGTH_SHORT).show();
        m.music();

    }
}
