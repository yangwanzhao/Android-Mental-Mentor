package com.example.noahtucker.depression_app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//need to add a feature that doesn't allow the user to continue if they did not take the quiz or did not select areas of stress

public class MainActivity extends AppCompatActivity {
    String uN = "";
    String pW = "";
    String full_name = "";
    EditText editText;
    EditText editText2;
    EditText editText12;
    EditText editText13;
    EditText editText8;
    EditText editText71;
    String stresses = "DEFAULT";
    public String[] links = new String[100];
    public int linkCount = 0;
    public String[] post = new String[100];
    public int postCount =0;
    public String[] id1 = new String[100];
    public int actCount =0;
    public String[] act = new String[100];
    public String[] id2 = new String [100];
    MainActivity a = this;
    String s1 = "0";
    int t1 = 0;
    String s2 = "0";
    int t2 = 0;
    String s3 = "0";
    int t3 = 0;
    String s4 = "0";
    int t4 = 0;
    String s5 = "0";
    int t5 = 0;
    String s6 = "0";
    int t6 = 0;
    String s7 = "0";
    int t7 = 0;
    String s8 = "0";
    int t8 = 0;
    String s9 = "0";
    int t9 = 0;
    int sum = 0;
    public String regResult = "";
    public String addResult = "";
    String score ="DEFAULT";
    int login_flag =0;
    public String[] friendship = new String[100];
    int gf_flag =0;
    public int view_flag =0;
    String radio_button ="";

    // for chat bot
    private List<Msg> msgList = new ArrayList<Msg>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    String chat_bot_reply;
    // end chat bot


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        a = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLoginScreen();

    }

    public void startLoginScreen() {
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);


        final Button loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                uN = editText.getText().toString();
                pW = editText2.getText().toString();
                login(uN, pW);
            }
        });
    }

    public void login(String username, String password) {
        SignInActivity log = new SignInActivity(username, password, this);
        log.execute(username, password);

    }

    public void regi() {
        setContentView(R.layout.registration);
        editText12 = (EditText) findViewById(R.id.editText12);
        editText13 = (EditText) findViewById(R.id.editText13);
        editText8 = (EditText) findViewById(R.id.editText8);
        editText71 = (EditText) findViewById(R.id.editText71);
        final Button cancel = (Button) findViewById(R.id.button5);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startLoginScreen();
            }
        });
        final Button regi = (Button) findViewById(R.id.button6);
        regi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uN = editText12.getText().toString();
                pW = editText13.getText().toString();
                full_name = editText71.getText().toString();
                String pW2 = editText8.getText().toString();
                if (!checkIfEntered(uN, pW, full_name)) {
                    Toast.makeText(MainActivity.this, "You did not enter correct data. Try again.", Toast.LENGTH_SHORT).show();
                } else {
//                    if (!checkUsernameResult(uN)) {
//                        Toast.makeText(MainActivity.this, "That username already exists.", Toast.LENGTH_SHORT).show();
//                    } else {
                    if (!checkPass(pW, pW2)) {
                        Toast.makeText(getApplicationContext(),
                                "Passwords do not match", Toast.LENGTH_LONG).show();
                    } else {
                        configuration();
                    }
                }
            }
            //}
        });
    }

    public void register(final View view) {
        regi();
//button5
    }

    public boolean checkIfEntered(String uN, String pW, String full_name) {
        if (uN.isEmpty() || pW.isEmpty() || full_name.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public boolean checkPass(String pw, String pw2) {
        if (pw.equals(pw2)) {
            return true;
        } else {
            return false;
        }
    }

    public void configuration() {
        setContentView(R.layout.extra_reg);
        final Button cancel = (Button) findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startLoginScreen();
            }
        });
        final Button b = (Button) findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Dialog d = dialog();
                //d.create();
            }
        });
//need code for the quiz here

        final Button ok = (Button) findViewById(R.id.button7);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(score != "DEFAULT" && stresses !="DEFAULT"){
                    register(uN, pW, full_name, stresses, score);
                }
                else if(stresses == "DEFAULT"){
                    Toast.makeText(MainActivity.this, "PLEASE FILL IN YOUR AREA OF STRESS", Toast.LENGTH_LONG).show();

                }
                else if(score == "DEFAULT"){
                    Toast.makeText(MainActivity.this, "PLEASE TAKE THE QUIZ ABOVE", Toast.LENGTH_LONG).show();
                }



            }

        });

    }

    public Dialog dialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = "Select your area of most stress";
        builder.setTitle(message);
        final String[] items = {"Education", "Family", "Work", "Social"};
        int checkedItem =0;
        stresses = "Education";
        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    stresses = items[which];
            }

        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "Your area of stress is: " + stresses, Toast.LENGTH_SHORT).show();

            }

        });
        builder.show();

        return builder.create();

    }


    public void cancel(View view) {
        setContentView(R.layout.activity_main);
    }

    public void loginSuccess() {
        setContentView(R.layout.login_success);
        login_flag=1;

    }

    public void makeLoginFailedToast() {
        Toast.makeText(MainActivity.this, "You entered incorrect login info. Please try again.", Toast.LENGTH_SHORT).show();
        startLoginScreen();

    }

    public void music(View v) {
        setContentView(R.layout.music_choice);
    }
    public void music(){setContentView(R.layout.music_choice);}
    private void clearLinks() {
        for (int yu = 0; yu < links.length; yu++) {
            links[yu] = ""; //initializes array
        }
    }

    public void christian(View V) {
        setContentView(R.layout.music);
        clearLinks();
        String type = "R";
        GetMusic gm = new GetMusic(type, a);
        gm.execute(type);
    }

    // four new types of classical music
    public void depression(View V) {
        setContentView(R.layout.music);
        clearLinks();
        String type = "D";
        GetMusic gm = new GetMusic(type, a);
        gm.execute(type);
    }

    public void anxiety(View V) {
        setContentView(R.layout.music);
        clearLinks();
        String type = "A";
        GetMusic gm = new GetMusic(type, a);
        gm.execute(type);
    }

    public void Insomnia(View V) {
        setContentView(R.layout.music);
        clearLinks();
        String type = "I";
        GetMusic gm = new GetMusic(type, a);
        gm.execute(type);
    }

    public void Neurasthenia(View V) {
        setContentView(R.layout.music);
        clearLinks();
        String type = "N";
        GetMusic gm = new GetMusic(type, a);
        gm.execute(type);
    }
    // four new types of classical music


    public void nonReligous(View v) {
        setContentView(R.layout.illness_category);
//        setContentView(R.layout.music);
//        clearLinks();
//        String type = "NR";
//        GetMusic gm = new GetMusic(type, a);
//        gm.execute(type);
    }

    public void postLinks() {
        final ListView listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, links);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert 
                //   Toast.makeText(getApplicationContext(),"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();

                //go to link
                if (!itemValue.isEmpty()) {
                    goToLink(itemValue);
                }

            }

        });
    }

    private void goToLink(String itemValue) {
        Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
        myWebLink.setData(Uri.parse(itemValue));
        startActivity(myWebLink);

    }

    public void back(View v) {
        setContentView(R.layout.music_choice);
    }
    public void back_ill_cate(View v) {
        setContentView(R.layout.music_choice);
    }

    public void mainMenu(View v) {
        setContentView(R.layout.login_success);
    }

    public void quiz(View v) {
        score ="DEFAULT";
        setContentView(R.layout.quiz);
        spinner();
        spinner2();
        spinner3();
        spinner4();
        spinner5();
        spinner6();
        spinner7();
        spinner8();
        spinner9();


    }

    public void spinner() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1 = parent.getItemAtPosition(position).toString();
                if(s1.startsWith("0")){
                    s1 = "0";
                }
                else if(s1.startsWith("1")){
                    s1= "1";
                }
                else if(s1.startsWith("2")){
                    s1 = "2";
                }
                else if(s1.startsWith("3")){
                    s1 = "3";
                }
                t1 = Integer.parseInt(s1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s1 = "0";
            }
        });


    }

    public void spinner2() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s2 = parent.getItemAtPosition(position).toString();
                if(s2.startsWith("0")){
                    s2 = "0";
                }
                else if(s2.startsWith("1")){
                    s2= "1";
                }
                else if(s2.startsWith("2")){
                    s2 = "2";
                }
                else if(s2.startsWith("3")){
                    s2 = "3";
                }
                t2 = Integer.parseInt(s2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s2 = "0";
            }
        });


    }

    public void spinner3() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner3);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s3 = parent.getItemAtPosition(position).toString();
                if(s3.startsWith("0")){
                    s3 = "0";
                }
                else if(s3.startsWith("1")){
                    s3= "1";
                }
                else if(s3.startsWith("2")){
                    s3 = "2";
                }
                else if(s3.startsWith("3")){
                    s3 = "3";
                }
                t3 = Integer.parseInt(s3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s3 = "0";
            }
        });


    }

    public void spinner4() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner4);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s4 = parent.getItemAtPosition(position).toString();
                if(s4.startsWith("0")){
                    s4 = "0";
                }
                else if(s4.startsWith("1")){
                    s4= "1";
                }
                else if(s4.startsWith("2")){
                    s4 = "2";
                }
                else if(s4.startsWith("3")){
                    s4 = "3";
                }
                t4 = Integer.parseInt(s4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s4 = "0";
            }
        });


    }

    public void spinner5() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner5);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s5 = parent.getItemAtPosition(position).toString();
                if(s5.startsWith("0")){
                    s5 = "0";
                }
                else if(s5.startsWith("1")){
                    s5= "1";
                }
                else if(s5.startsWith("2")){
                    s5 = "2";
                }
                else if(s5.startsWith("3")){
                    s5 = "3";
                }
                t5 = Integer.parseInt(s5);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s5 = "0";
            }
        });


    }

    public void spinner6() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner6);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s6 = parent.getItemAtPosition(position).toString();
                if(s6.startsWith("0")){
                    s6 = "0";
                }
                else if(s6.startsWith("1")){
                    s6= "1";
                }
                else if(s6.startsWith("2")){
                    s6 = "2";
                }
                else if(s6.startsWith("3")){
                    s6 = "3";
                }
                t6 = Integer.parseInt(s6);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s6 = "0";
            }
        });


    }

    public void spinner7() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner7);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s7 = parent.getItemAtPosition(position).toString();
                if(s7.startsWith("0")){
                    s7 = "0";
                }
                else if(s7.startsWith("1")){
                    s7= "1";
                }
                else if(s7.startsWith("2")){
                    s7 = "2";
                }
                else if(s7.startsWith("3")){
                    s7 = "3";
                }
                t7 = Integer.parseInt(s7);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s7 = "0";
            }
        });


    }

    public void spinner8() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner8);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s8 = parent.getItemAtPosition(position).toString();
                if(s8.startsWith("0")){
                    s8 = "0";
                }
                else if(s8.startsWith("1")){
                    s8= "1";
                }
                else if(s8.startsWith("2")){
                    s8 = "2";
                }
                else if(s8.startsWith("3")){
                    s8 = "3";
                }
                t8 = Integer.parseInt(s8);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s8 = "0";
            }
        });


    }

    public void spinner9() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner9);
        ArrayList<String> freq = new ArrayList<>();
        freq.add("0: Not at all");
        freq.add("1: Several Days");
        freq.add("2: More Than Half the Days");
        freq.add("3: Nearly Every Day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, freq);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s9 = parent.getItemAtPosition(position).toString();
                if(s9.startsWith("0")){
                    s9 = "0";
                }
                else if(s9.startsWith("1")){
                    s9= "1";
                }
                else if(s9.startsWith("2")){
                    s9 = "2";
                }
                else if(s9.startsWith("3")){
                    s9 = "3";
                }
                t9 = Integer.parseInt(s9);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s9 = "0";
            }
        });


    }

    public void sum(View v) {
        sum = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;
         score = calcScore(sum);
        Toast.makeText(getApplicationContext(),
                "Your total score: " + sum + ". Your diagnosis is: " + score, Toast.LENGTH_LONG).show();

        //call extra reg
        if(login_flag ==0) {
            configuration();
        }
        else{
            extraRegi();
        }
    }

    private String calcScore(int sum) {
         String score1 = "None";
        if (sum >= 5 && sum <= 9) score1 = "Minimal_Symptoms";
        if (sum >= 10 && sum <= 14) score1 = "Mild";
        if (sum >= 15 && sum <= 19) score1 = "Moderately_Severe";
        if (sum >= 20) score1 = "Severe";

        return score1;
    }

    public void success1(String s) {
        if (s.startsWith("1")) {
            Toast.makeText(MainActivity.this, "You are now registered. Try logging in.", Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.activity_main);
            startLoginScreen();
        }
        else{
            Toast.makeText(MainActivity.this, "Username already exists. Please enter a different one", Toast.LENGTH_LONG).show();
                //setContentView(R.layout.registration);
                //  setContentView(R.layout.login_success);
            stresses = "DEFAULT";
            score ="DEFAULT";
            regi();

            }


    }
    public void register(String uN, String pW, String full_name, String area, String level) {
        Registration reg = new Registration(uN, pW, full_name, area, level, this);
        reg.execute(uN, pW, full_name, area, level);
    }
    public void forum(View v){
        forum1();


    }
    public void forum1(){
        setContentView(R.layout.posting_forum);
        //get the area of stress then post based on that
        for (int yu = 0; yu < post.length; yu++) {
            post[yu] = ""; //initializes array
            id1[yu] = "";
        }
        Forum f = new Forum(uN, this);
        f.execute(uN);
    }

    public void postForum() {
        final ListView listView2 = (ListView) findViewById(R.id.list2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, post);
        // Assign adapter to ListView
        listView2.setAdapter(adapter);
        // ListView Item Click Listener
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) listView2.getItemAtPosition(itemPosition);


                //go to link
                if (!itemValue.isEmpty()) {
                    //create dialog with the name of who you want to add
                   Dialog d = friendDialog(id1[itemPosition]);
                }


            }

        });
    }

    private Dialog friendDialog(final String s) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to add \""+ s + "\" as a friend?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //add the friend????
                for(int y =0; y < friendship.length; y++){
                    friendship[y] = "";
                }
               // GetFriends gf = new GetFriends(uN, a);
               // gf.execute(uN);
                AddFriends af = new AddFriends(uN, s, a);
                af.execute(uN, s);
                postForum();



            }
        });
        builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //send them back to the last page with
                postForum();

            }
        });
        builder.show();


        return builder.create();
    }
    private Dialog friendDialog2(final String s) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to add \""+ s + "\" as a friend?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //add the friend????
                for(int y =0; y < friendship.length; y++){
                    friendship[y] = "";
                }

                AddFriends af = new AddFriends(uN, s, a);
                af.execute(uN, s);
            }
        });
        builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //send them back to the last page with
                postAct();

            }
        });
        builder.show();


        return builder.create();
    }

    public void cancel5(View v){
        forum(v);
    }
    public void addPost(View v){
        setContentView(R.layout.add_post);

    }
    public void submit12(View v){
        EditText editText = (EditText) findViewById(R.id.editText80);
        String len = editText.getText().toString();
        if(len.length() > 250 ){
            Toast.makeText(MainActivity.this, "You entered "+ len.length() +" characters. Please only enter 250.", Toast.LENGTH_SHORT).show();

        }
        else if(!len.isEmpty()){
//            Toast.makeText(MainActivity.this, "Less than 250 " + uN, Toast.LENGTH_SHORT).show();
            Dialog d = dialog2(len);
            //d.create();

        }
        else{
            Toast.makeText(MainActivity.this, "Please enter in the box above", Toast.LENGTH_SHORT).show();
        }


    }
    public void submit13(View v){
        EditText editText = (EditText) findViewById(R.id.editText800);
        String len = editText.getText().toString();
        String l = len.toUpperCase();
        if(l.contains("M.")){
            String h = "https://www.";

            if(l.startsWith("HTTPS://M.")) {
                String sub = len.substring(10);
                h+= sub;
                if(radio_button == "R" || radio_button =="NR") {

                    CheckLink cl = new CheckLink(h, radio_button, a);
                    cl.execute(h);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please select whether music is religous or non-religous.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(!l.contains("YOUTUBE.COM")){
            Toast.makeText(MainActivity.this, "123 Please enter a https://youtube.com/... link or https://m.youtube.com/...", Toast.LENGTH_SHORT).show();
        }
        else if(!l.contains("HTTPS://")){
            String h = "https://";
            h += len;

            if(radio_button == "R" || radio_button =="NR") {
                CheckLink cl = new CheckLink(h, radio_button, a);
                cl.execute(h);
            }
            else{
                Toast.makeText(MainActivity.this, "Please select whether music is religous or non-religous.", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            if(radio_button == "R" || radio_button =="NR") {
            CheckLink cl = new CheckLink(len,radio_button, a);
            cl.execute(len);
            }
            else{
                Toast.makeText(MainActivity.this, "Please select whether music is religous or non-religous.", Toast.LENGTH_SHORT).show();
            }

        }

    }



    private Dialog dialog2(final String len) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure this what you want to post?");
        builder.setMessage(len);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //post the message
                //we have the message and the username, we need to get the areas_of_stresses
                AddPost ap = new AddPost(uN, len, a);
                ap.execute(uN,len);
            }
        });
        builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //send them back to the last page with
                redo_Post(len);
            }
        });
        builder.show();


        return builder.create();
    }
    public void redo_Post(String len){
        setContentView(R.layout.add_post);
        EditText e = (EditText) findViewById(R.id.editText80);
        e.setText(len);
    }

    public void success2(String s) {
        if (s.startsWith("1")) {
            Toast.makeText(MainActivity.this, "Your post has been added.", Toast.LENGTH_SHORT).show();
            forum1();
        }
        else{
            Toast.makeText(MainActivity.this, "Error in php.", Toast.LENGTH_LONG).show();

        }
    }
    public void success3(String s){
        if (s.startsWith("1")) {
            Toast.makeText(MainActivity.this, "Your post has been added.", Toast.LENGTH_SHORT).show();
            forum2();
        }
        else{
            Toast.makeText(MainActivity.this, "Error in php.", Toast.LENGTH_LONG).show();

        }
    }

    public void forum2() {
        setContentView(R.layout.suggested_activities);
        //get the area of stress then post based on that
        for (int yu = 0; yu < act.length; yu++) {
           act[yu] = ""; //initializes array
            id2[yu] = "";
        }
        GetPostedActivity gp = new GetPostedActivity(uN, this);
        gp.execute(uN);


    }

    public void extraReg(View v){
        extraRegi();


    }

    public void makeUpdateToast() {
        Toast.makeText(MainActivity.this, "Your information has been updated.", Toast.LENGTH_LONG).show();
        loginSuccess();

    }
    public void extraRegi(){
        setContentView(R.layout.extra_reg);

        final Button cancel = (Button) findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginSuccess();
            }
        });
        final Button b = (Button) findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Dialog d = dialog();
                //d.create();
            }
        });
//need code for the quiz here

        final Button ok = (Button) findViewById(R.id.button7);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(score != "DEFAULT" && stresses !="DEFAULT"){
                    //update registration
                    UpdateReg ur = new UpdateReg(uN, stresses, score, a);
                    ur.execute(uN, stresses, score);
                }
                else if(stresses == "DEFAULT"){
                    Toast.makeText(MainActivity.this, "PLEASE FILL IN YOUR AREA OF STRESS", Toast.LENGTH_LONG).show();

                }
                else if(score == "DEFAULT"){
                    Toast.makeText(MainActivity.this, "PLEASE TAKE THE QUIZ ABOVE", Toast.LENGTH_LONG).show();
                }



            }

        });

    }


    public void viewFriends(View v){
        setContentView(R.layout.view_friends);
        for(int y =0; y < friendship.length; y++){
            friendship[y] = "";
        }
        view_flag =1;
            GetFriends gf = new GetFriends(uN, a);
            gf.execute(uN);

    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello. How are you doing today?", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
    }

    private void talk2ChatBot(String content){
        Talk2Bot agent = new Talk2Bot(content, a);
        agent.execute();
    }

    public void show_msg(String content, String in_out){
        if (!"".equals(content)) {
            Msg msg = new Msg(content, in_out=="SENT"?Msg.TYPE_SENT:Msg.TYPE_RECEIVED);
            msgList.add(msg);
            // 当有新消息时，刷新ListView中的显示
            adapter.notifyItemInserted(msgList.size() - 1);
            // 将ListView定位到最后一行
            msgRecyclerView.scrollToPosition(msgList.size() - 1);
            // 清空输入框中的内容
            inputText.setText("");
        }
    }

    public void chatBot(View v){
        setContentView(R.layout.chat_window);
        initMsgs(); // 初始化消息数据
        inputText = (EditText) findViewById(R.id.msg_recycle_input);
        send = (Button) findViewById(R.id.msg_recycle_send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);

        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                System.out.println("Younger: "+content+"\n");
                show_msg(content, "SENT");

                // Send to Chat Bot API and print
                talk2ChatBot(content);
                // End
            }
        });

    }

    public void evaluate(View view) {
        evaluate_bot buttonDialogFragment = new evaluate_bot();
        buttonDialogFragment.show("Hi,", "Am I helpful?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Mental Mentor is learning", Toast.LENGTH_SHORT).show();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Mental Mentor is learning", Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
        setContentView(R.layout.login_success);
    }


    public void addMusic(View v){
        setContentView(R.layout.add_music);
        radio_button ="";
    }
    public void cancel6(View v){
        setContentView(R.layout.music_choice);
    }
    public void radioButtonClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.radioButton3:
                if (checked)
                    radio_button = "R";
                    break;
            case R.id.radioButton4:
                if (checked)
                    radio_button ="NR";
                    break;
        }


    }
    public void suggestedActivities(View v){
        setContentView(R.layout.suggested_activities);
        for (int yu = 0; yu < act.length; yu++) {
            act[yu] = ""; //initializes array
            id2[yu] = "";
        }
        GetPostedActivity gp = new GetPostedActivity(uN, this);
        gp.execute(uN);



    }
    public void cancel7(View v){
        setContentView(R.layout.suggested_activities);
        forum2();
    }
    public void submit123(View v){
        EditText editText = (EditText) findViewById(R.id.editText90);
        String len = editText.getText().toString();
        if(len.length() > 250 ){
            Toast.makeText(MainActivity.this, "You entered "+ len.length() +" characters. Please only enter 250.", Toast.LENGTH_SHORT).show();

        }
        else if(!len.isEmpty()){
//            Toast.makeText(MainActivity.this, "Less than 250 " + uN, Toast.LENGTH_SHORT).show();
            Dialog d = dialog3(len);
            //d.create();

        }
        else{
            Toast.makeText(MainActivity.this, "Please enter in the box above", Toast.LENGTH_SHORT).show();
        }
    }

    private Dialog dialog3(final String len) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure this what you want to post?");
        builder.setMessage(len);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //post the message
                //we have the message and the username, we need to get the areas_of_stresses
                AddActivity av = new AddActivity(uN, len,a);
                av.execute(uN, len);


            }
        });
        builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //send them back to the last page with
                redo_Post1(len);
            }
        });
        builder.show();


        return builder.create();
    }

    private void redo_Post1(String len) {
        setContentView(R.layout.add_activity);
        EditText e = (EditText) findViewById(R.id.editText90);
        e.setText(len);
    }

    public void postAct() {
        final ListView listView2 = (ListView) findViewById(R.id.list21);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, act);
        // Assign adapter to ListView
        listView2.setAdapter(adapter);
        // ListView Item Click Listener
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) listView2.getItemAtPosition(itemPosition);


                //go to link
                if (!itemValue.isEmpty()) {
                    //create dialog with the name of who you want to add
                    Dialog d = friendDialog2(id2[itemPosition]);
                }
            }

        });
    }
    public void addAct(View v){
        setContentView(R.layout.add_activity);
    }
}
