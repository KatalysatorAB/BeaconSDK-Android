package com.katalysator.katexampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.glimr.sdk.engine.KATEvent;
import io.glimr.sdk.engine.KATManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // init the manager
        KATManager manager = KATManager.getInstance();
        manager.init(this.getApplicationContext(), "API_TOKEN", 30, false, 90);

        // start monitoring locations and ibeacons
        KATManager.getInstance().startMonitoring();

        // load tags for the current device
        KATManager.getInstance().setAudiencesAndGeotagsCallback(new KATEvent() {
            @Override
            public void availableAudiencesUpdated(HashMap<String, ArrayList<String>> usertags) {
                // raw response
                Log.i("response", "availableAudiencesUpdated raw: " + usertags);

                // raw values
                Log.i("response", "availableAudiencesUpdated list: " + KATManager.mapToArrayList(usertags));

                // helper method to create a url query string from the mapping
                Log.i("response", "availableAudiencesUpdated query string: " + KATManager.map2QueryString(usertags));
            }
        });
        KATManager.getInstance().getAudiencesAndGeotags();
    }

    @Override
    protected void onPause() {
        super.onPause();
        KATManager.getInstance().stopMonitoring();
    }

}