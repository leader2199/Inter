package com.example.admin.inter;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    ListView lview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lview=this.findViewById(R.id.lview);
        as();
    }

    private void as(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                String str=webOpen.takeSever("https://gist.githubusercontent.com/Goles/3196253/raw/9ca4e7e62ea5ad935bb3580dc0a07d9df033b451/CountryCodes.json");
                Message msg = new Message();
                msg.obj=str;
                handler.sendMessage(msg);


            }
        };
        thread.start();
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = msg.obj.toString();
            couModel couModel;

            ArrayList<couModel> arr = new ArrayList<>();
            try {
                JSONArray countries = new JSONArray(str);
                for (int i = 0; i <25 ; i++) {
                    JSONObject name = (JSONObject) countries.get(i);
                    couModel=new couModel(R.drawable.ic_launcher_background,name.getString("name"));
                    arr.add(couModel);
                }
                couAd couAd = new couAd(MainActivity.this,R.layout.activity_main,arr);
                lview.setAdapter(couAd);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };












}
