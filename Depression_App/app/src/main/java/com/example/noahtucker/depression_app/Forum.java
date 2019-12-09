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
 * Created by noahtucker on 4/25/17.
 */

public class Forum extends AsyncTask<String, Void, String> {
        String username;
        MainActivity m;
    public Forum(String username, MainActivity m){
        this.username = username;
        this.m =m;
    }
@Override
protected String doInBackground(String...params){
        try {
        String method = "forum";
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
        }catch(Exception e){
        System.out.println("My Result:" + e.getMessage() );

        return new String("Exception: " + e.getMessage());
        }
        }
        @Override
        public void onPostExecute(String s){

                if(m.post[0] == ""){
                        m.postCount =0;
                }
                //creates array of posts
                // post < id ;
                int postTemp =0;
                for(int j=0; j<s.length();j++) {
                        if (s.charAt(j) == '<') {
                                postTemp++;
                        }
                        else if(s.charAt(j) == '>'){
                                m.postCount++;
                        }
                        if(postTemp == m.postCount && s.charAt(j) != '>') {
                                m.post[m.postCount] += s.charAt(j);
                        }
                        else if(postTemp > m.postCount && s.charAt(j) != '<'){
                                m.id1[m.postCount]+= s.charAt(j);
                        }

                }
                for(int ij=0; ij < m.post.length; ij++ ){
                        if(m.post[ij] != "") {
                                m.post[ij] += "\n\nPosted by: " + m.id1[ij];

                        }

                }
               m.postForum();


        }
}
