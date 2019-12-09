package com.example.noahtucker.depression_app;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;

/**
 * Created by noahtucker on 4/13/17.
 */

public class SignInActivity extends AsyncTask<String, Void, String> {
    String u;
    String p;
    MainActivity m;
    public SignInActivity(String username, String password, MainActivity main){
        this.u = username;
        this.p = password;
        this.m = main;
    }
    @Override
    protected String doInBackground(String...params){


        try {
            String method = "login";
            String username = this.u;
            String password = this.p;
            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
            data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

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
            Log.d("Younger:", sb.toString());

            return sb.toString();
        }catch(Exception e){
            System.out.println("Younger:" + e.getMessage() );

            return new String("Exception: " + e.getMessage());
        }
    }
    @Override
    public void onPostExecute(String s){
        if(s.startsWith("Succeed")){
            m.loginSuccess();
        }
        else{
            m.makeLoginFailedToast();
        }
    }
}
