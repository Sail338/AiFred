package com.example.myapplication2.app;

import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hari on 10/3/15.
 */
public  class CalenderCalls extends AsyncTask<Void, Void, List<String>> {

    private Calendar  service = null;


    public CalenderCalls(GoogleAccountCredential credential){


        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        service = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, credential)
                .setApplicationName("Google Calendar API Android Quickstart")
                .build();
    }
    @Override
    protected List<String> doInBackground(Void... params) {
        try {
            return getDataFromApi();
        } catch (Exception e) {
            e.printStackTrace();
            cancel(true);
            return null;
        }
    }
    public List<String>  getDataFromApi() throws IOException {

        DateTime dt = new DateTime(System.currentTimeMillis());
        List<String> info = new ArrayList<>();
        Events events = service.events().list("primary").setMaxResults(100).execute();
        List<Event> eventList = events.getItems();
        for(Event event : eventList){
            System.out.println(event);
            info.add(event.toString());

        }
        Log.i("fuck",info.get(0));
        return info;



    }



    }



