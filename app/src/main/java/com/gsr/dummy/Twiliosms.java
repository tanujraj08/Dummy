package com.gsr.dummy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Twiliosms extends Main2Activity {
    private EditText mTo;
    private TextView mBody;
    private Button mSend;
    private OkHttpClient mClient = new OkHttpClient();
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twiliosms);


        mTo = findViewById(R.id.phone_no);
        mBody = findViewById(R.id.Msg_text);
        mSend = findViewById(R.id.send);
        mContext = getApplicationContext();
        String str2 = getIntent().getStringExtra("DATA");
        mBody.setText(str2);
        



        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Your authentication key
                String authkey = "235271AX2SCGheP5b8cc34c";
//Multiple mobiles numbers separated by comma
                String mobiles = "7004091997";
//Sender ID,While using route4 sender id should be 6 characters long.
                String senderId = "GSRIND";
//Your message to send, Add URL encoding here.
                String message = "Test message";
//define route
                String route="default";

                URLConnection myURLConnection=null;
                URL myURL=null;
                BufferedReader reader=null;

//encoding message
                String encoded_message= URLEncoder.encode(message);

//Send SMS API
                String mainUrl="http://api.msg91.com/api/sendhttp.php?";

//Prepare parameter string
                StringBuilder sbPostData= new StringBuilder(mainUrl);
                sbPostData.append("authkey="+authkey);
                sbPostData.append("&mobiles="+mobiles);
                sbPostData.append("&message="+encoded_message);
                sbPostData.append("&route="+route);
                sbPostData.append("&sender="+senderId);

//final string
                mainUrl = sbPostData.toString();
                try
                {
                    //prepare connection
                    myURL = new URL(mainUrl);
                    myURLConnection = myURL.openConnection();
                    myURLConnection.connect();
                    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));

                    //reading response
                    String response;
                    while ((response = reader.readLine()) != null)
                        //print response
                        Log.d("RESPONSE", ""+response);

                    //finally close connection
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }





}
