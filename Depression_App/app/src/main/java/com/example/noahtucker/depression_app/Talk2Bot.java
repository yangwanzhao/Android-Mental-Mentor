package com.example.noahtucker.depression_app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;

public class Talk2Bot extends AsyncTask<String, Void, String> {
    String content;
    MainActivity m;
    public Talk2Bot(String cont, MainActivity main){
        this.content = cont;
        this.m = main;
    }
    @Override
    protected String doInBackground(String...params){
        HttpURLConnection connection = null;
        try {
            String url_encode_str = new String(content.getBytes(), "UTF-8");
            url_encode_str = URLEncoder.encode(url_encode_str, "UTF-8");

            Calendar c = Calendar.getInstance();
            String year = String.valueOf(c.get(Calendar.YEAR));
            String month = String.valueOf(c.get(Calendar.MONTH)+1);
            String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            month = month.length()==1?"0"+month:month;
            day = day.length()==1?"0"+day:day;

            // date in the link and the auth code need to be changed
            String link = "https://api.wit.ai/message?v="+year+month+day+"&q="+url_encode_str;
            String auth = "Bearer Y64F3DN6SHDLAHXL4SG2I6FHUMXS4VH3";

            URL url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            connection.addRequestProperty ("AUTHORIZATION", auth);

            StringBuilder response = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {

                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while (null != (line = reader.readLine())) {
                    response.append(line);
                }
            }
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return new String("Exception: " + e.getMessage());
        } finally {
            if (null!= connection) {
                connection.disconnect();
            }
        }
    }
    @Override
    public void onPostExecute(String s){
        // get response from chatbot and parse from json
        System.out.print("Talk2Bot: "+s+"\n");

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject entities = new JSONObject(jsonObject.getString("entities"));

            JSONArray intent_array = new JSONArray(entities.getString("intent"));
            JSONObject intent_first = intent_array.getJSONObject(0);
            String intent_value = intent_first.getString("value");

            String illness_type_value = "";
            if (!entities.isNull("illness_type")){
                JSONArray illness_type_array = new JSONArray(entities.getString("illness_type"));
                JSONObject illness_type_first = illness_type_array.getJSONObject(0);
                illness_type_value = illness_type_first.getString("value");
            }

            String reply = null;
            if ("complain".equals(intent_value)){
                if (!"".equals(illness_type_value)){
                    reply = "Listening to Mozart may help your " + illness_type_value;
                    System.out.println("Mental Mentor: "+reply+"\n");
                }
                else{
                    reply = "Could you tell me what's going on?";
                    System.out.println("Mental Mentor: "+reply+"\n");
                }

            }

            m.show_msg(reply, "RECEIVED");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}