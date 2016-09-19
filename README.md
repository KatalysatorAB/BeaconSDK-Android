Beacup-Android
==============

Documentation
============

For documentation visit http://developer.glimr.io

Minimum setup steps
============

Add the Glimr SDK dependency in your build.gradle:

    compile 'io.glimr.sdk:glimr-geo:1.8.8'



The example app is built with gradle and if you use a gradle compatible IDE the example app 
will just work if you import it as a gradle project.


If you want to integrate the SDK into your own app add the following lines:

    <receiver android:enabled="true"
              android:exported="true"
              android:isolatedProcess="false"
              android:label="iBeacon"
              android:name="io.glimr.sdk.beacon.service.KATBeaconReceiver">
    </receiver>

    <service android:enabled="true"
             android:isolatedProcess="false"
             android:label="iBeacon"
             android:name="io.glimr.sdk.beacon.service.IBeaconService">
    </service>

    <service android:enabled="true"
             android:name="io.glimr.sdk.beacon.IBeaconIntentProcessor">
        <meta-data android:name="background" android:value="true" />
    </service>

    <service android:enabled="true"
             android:name="io.glimr.sdk.geofence.KATGeofenceIntentService"
             android:exported="true">
    </service>
	
		
The SDK requires these permissions:
	
  	<uses-permission android:name="android.permission.BLUETOOTH"/>
	<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
	<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

At this moment the SDK depends on the gson library, which you can add as a maven 
dependency in gradle (as in the example project) or download the jar and add it as a dependency 
in your own project.

