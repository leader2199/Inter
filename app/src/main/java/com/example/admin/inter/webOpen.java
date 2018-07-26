package com.example.admin.inter;

import android.graphics.drawable.Drawable;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

public class webOpen {
    public static String takeSever(String str){
        String rel=null;
        int CONNECT=3000;
        int SOCKET=5000;
        HttpParams httpParams= new BasicHttpParams() ;
        HttpConnectionParams.setConnectionTimeout(httpParams,CONNECT);
        HttpConnectionParams.setSoTimeout(httpParams,SOCKET);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(str);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse!=null){
                InputStream inputStream = httpResponse.getEntity().getContent();
                rel=pRog(inputStream);
            }
        }catch (ConnectTimeoutException cx){
            rel="connect";

        }catch (SocketTimeoutException sx){
            rel="socket";
        }
        catch (Exception e) {
            rel="php";
        }
        return rel;

    }

    private static String pRog(InputStream inputStream) throws IOException {
        String line="";
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while((line=bufferedReader.readLine())!=null){
            builder.append(line);
        }
        return builder.toString();
    }



}
