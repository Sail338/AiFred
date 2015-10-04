package com.example.myapplication2.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.testing.http.apache.MockHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hari on 10/3/15.
 */
public class AlarmReciver extends BroadcastReceiver  {
    @Override
    public void onReceive(final Context context, Intent intent){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();
     //   send();
        new Thread(new Runnable() {
            @Override
            public void run() {
             send();
            }
        }).start();



    }
    public void send() {


            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://ff7a742c.ngrok.io/aifred/anything.php");
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                // we wont be receiving the parameter ID in your server, but it is here to show you how you can send more data

                // message is the parameter we are receiving, it has the value of 1 which is the value that will be sent from your server to your Arduino board
                nameValuePairs.add(new BasicNameValuePair("message", "1"));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                client.execute(post); // send the parameter to the server
            }
            catch (Exception e){

            }

        }
    }