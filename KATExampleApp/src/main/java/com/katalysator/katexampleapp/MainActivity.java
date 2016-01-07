package com.katalysator.katexampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.katalysator.sdk.engine.KATEvent;
import com.katalysator.sdk.engine.KATManager;

import java.util.ArrayList;

public class MainActivity extends Activity implements KATEvent {

    KATManager mKatManager;

    TextView range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        range = (TextView) findViewById(R.id.rangeTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // init the manager
        mKatManager = KATManager.getInstance(this, "API_TOKEN", this, false, 30);

        // start monitoring for geofences and beacon ids configured on dashboard
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
    public void availableAudiencesUpdated(ArrayList<String> usertags) {
        Log.i("usertags", usertags.toString());
    }
}