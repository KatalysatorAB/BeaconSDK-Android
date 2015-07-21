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
        mKatManager = KATManager.getInstance(this, "B3945743-D258-49D0-AFBF-1E409AE59501", this);

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


    /**
     * If data is specfied to return, it will return here
     *
     * @param assets
     */
    @Override
    public void regionDataReceived(KATAssets assets, boolean isDelayed) {
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
    public void availableAudiencesUpdated(ArrayList<String> usertags) {
        Log.i("usertags", usertags.toString());
    }
}