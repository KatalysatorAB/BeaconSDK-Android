package com.katalysator.katexampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.glimr.sdk.engine.KATEvent;
import io.glimr.sdk.engine.KATManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements KATEvent {

    KATManager mKatManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // init the manager
        mKatManager = KATManager.getInstance(this, "API_TOKEN", this, false, 30);

        // start monitoring locations and ibeacons
        mKatManager.startMonitoring();

        // load tags for the current device
        mKatManager.getAudiencesAndGeotags();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mKatManager.stopMonitoring();
    }

    @Override
    public void availableAudiencesUpdated(HashMap<String, ArrayList<String>> map) {
        // raw response
        Log.i("response", "availableAudiencesUpdated raw: " + map);

        // raw values
        Log.i("response", "availableAudiencesUpdated list: " + KATManager.mapToArrayList(map));

        // helper method to create a url query string from the mapping
        Log.i("response", "availableAudiencesUpdated query string: " + KATManager.map2QueryString(map));
    }
}