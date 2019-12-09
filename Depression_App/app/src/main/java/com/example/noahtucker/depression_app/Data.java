package com.example.noahtucker.depression_app;

import android.app.Application;

public class Data extends Application {
    private String link;

    public String getLink(){
//        return "http://128.180.235.86:8888/demo/index.php";
        return "http://192.168.0.193:8888/demo/index.php";
//        "http://carina.cse.lehigh.edu/Depression/"; //add php and to MAMP to get it workingt
    }

//    @Override
//    public void onCreate(){
//        link = "http://128.180.235.86:8888/demo/index.php";
//
//        super.onCreate();
//    }
}
