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
import android.widget.TextView;
import com.katalysator.sdk.KATAdvertImageActivity;
import com.katalysator.sdk.KATAssets;
import com.katalysator.sdk.KATBeaconEvent;
import com.katalysator.sdk.KATBeaconManager;

public class MainActivity extends Activity implements KATBeaconEvent {

	KATBeaconManager mKatBeaconManager;
	TextView range;

	private static final String sBeaconToken = "AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA";
	private static final String sApiToken = "BB6AED33-6E33-2685-D759-F989C0ED1C11";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		range = (TextView) findViewById(R.id.rangeTextView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mKatBeaconManager = KATBeaconManager.getInstance(this);
		mKatBeaconManager.bindBeacons(sBeaconToken, sApiToken, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mKatBeaconManager.unBindBeacons();
	}

	/**
	 * Range from the beacon in meters
	 *
	 * @param rangeInMeter
	 */
	@Override
	public void rangeInMeters(double rangeInMeter) {
		range.setText(String.valueOf(rangeInMeter));
	}

	/**
	 * If data is specfied to return, it will return here
	 * @param assets
	 */
	@Override
	public void dataFromBeaconsReceived(KATAssets assets) {

	}

	/**
	 * Notifications event will com through here,
	 * this can happen both in background and foreground
	 *
	 * @param assets
	 * @param fullscreenActivity
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void notificationShouldDisplay(KATAssets assets, Class fullscreenActivity) {
		Intent intent = KATAdvertImageActivity.getIntentWithParameters(MainActivity.this, assets);

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
	 *
	 * @param fullscreenImageActivity
	 * @param assets
	 */
	@Override
	public void fullScreenImageShouldDisplay(Class fullscreenImageActivity, KATAssets assets) {
		Intent intent = KATAdvertImageActivity.getIntentWithParameters(MainActivity.this, assets);
		MainActivity.this.startActivity(intent);
	}
}
