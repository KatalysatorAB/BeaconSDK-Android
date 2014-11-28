package com.katalysator.katexampleapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.katalysator.sdk.advertisement.KATAdvertisement;
import com.katalysator.sdk.engine.KATActionType;
import com.katalysator.sdk.engine.KATAdvertImageActivity;
import com.katalysator.sdk.engine.KATAssets;
import com.katalysator.sdk.engine.KATEvent;
import com.katalysator.sdk.engine.KATManager;

import java.util.ArrayList;
import java.util.HashMap;

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
        mKatManager = KATManager.getInstance(this, "AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA", this);

        // To target just one single beacon id
        // mKatManager.setBeaconToken("AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA");

        // start monitoring for geofences and beacon ids configured on dashboard
        mKatManager.startMonitoring(this);

        // load dictionary for ad information
        mKatManager.loadAd();

        // load tags for the current device
        mKatManager.audienceTags();

        // get to know the device better over time
        mKatManager.conclude();

        // option to add aditional information about a device
        HashMap keyValue = new HashMap();
        keyValue.put("username", "team@glimr.io");
        mKatManager.enrichProfile(keyValue);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mKatManager.stopMonitoring();
    }

    /**
     * Range from the beacon in meters
     */
    @Override
    public void rangeInMeters(double rangeInMeter) {
        range.setText(String.valueOf(rangeInMeter));
    }

    /**
     * If data is specfied to return, it will return here
     */
    @Override
    public void dataFromBeaconsReceived(KATAssets assets) {
        mKatManager.trackAction(KATActionType.KATActionTypeEnter);
        Log.i("Asset Data", assets.toString());
    }

    /**
     * Notifications event will com through here,
     * this can happen both in background and foreground
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void notificationShouldDisplay(KATAssets assets, Class fullscreenActivity) {
        Intent intent;
        if (fullscreenActivity != null) {
            intent = KATAdvertImageActivity.getIntentWithParameters(MainActivity.this, assets);
        } else {
            intent = this.getIntent();
        }

        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Notification n = new Notification.Builder(MainActivity.this).
                setSmallIcon(R.drawable.ic_launcher).
                setContentTitle("Application Name").
                setContentIntent(pIntent).
                setContentText(assets.getTitle()).
                setAutoCancel(false).
                build();

        NotificationManager notificationManager = (NotificationManager)
                MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);
    }

    /**
     * If a fullscreen image is supposed to show this event will trigger.
     * The class fullscreenImageActivity is part of the sdk, but you could easily roll your own.
     * KATAssets is fully Serializable.
     */
    @Override
    public void fullScreenImageShouldDisplay(Class fullscreenImageActivity, KATAssets assets) {
        Intent intent = KATAdvertImageActivity.getIntentWithParameters(MainActivity.this, assets);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void advertisementUpdated(KATAdvertisement advertisement) {
        Log.i("advertisement", advertisement.toString());
        mKatManager.adAction(advertisement);
    }

    @Override
    public void availableAudienceTagsReceived(ArrayList<String> usertags) {
        Log.i("usertags", usertags.toString());
    }
}