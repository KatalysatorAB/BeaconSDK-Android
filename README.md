Beacup-Android
==============

Documentation
============

For documentation visit http://katalysatorab.github.io/

Minimum setup steps
============

The SDK is provided with the example app in 

	KATExampleApp/src/main/libs/katalysator_android_sdk.jar


The example app is built with gradle and if you use a gradle compatible IDE the example app 
will just work if you import it as a gradle project.


If you want to integrate the SDK into your own app add the following lines:

    <service android:enabled="true"
             android:isolatedProcess="false"
             android:label="iBeacon"
             android:name="com.katalysator.sdk.beacon.service.IBeaconService">
    </service>

    <service android:enabled="true"
             android:name="com.katalysator.sdk.beacon.IBeaconIntentProcessor">
        <meta-data android:name="background" android:value="true" />
        <intent-filter
                android:priority="0" >
            <action android:name="com.katalysator.sdk.DID_RANGING" />
            <action android:name="com.katalysator.sdk.DID_MONITORING" />
        </intent-filter>
    </service>

    <service android:enabled="true"
             android:name="com.katalysator.sdk.geofence.KATGeofenceIntentService"
             android:exported="true">
    </service>
		
		
If you want to use the built in full screen activity you also have to add:

        <activity android:name="com.katalysator.sdk.engine.KATAdvertImageActivity"></activity>
	
	
The SDK requires these permissions:
	
  	<uses-permission android:name="android.permission.BLUETOOTH"/>
	<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
	<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

At this moment the SDK depends on the gson library, which you can add as a maven 
dependency in gradle (as in the example project) or download the jar and add it as a dependency 
in your own project.


Also note the following lines might be needed depending on the use case as part of the tags for the main activity

    <intent-filter>
        <action android:name="com.katalysator.sdk.START_FROM_NOTIFICATION" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
