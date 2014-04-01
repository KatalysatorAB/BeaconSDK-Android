Beacup-Android
==============

This is the beta Katalysator Beacon SDK for Android


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
				 android:name="com.katalysator.sdk.service.IBeaconService">
		</service>

		<service android:enabled="true"
				 android:name="com.katalysator.sdk.IBeaconIntentProcessor">
			<meta-data android:name="background" android:value="true" />
			<intent-filter
					android:priority="0" >
				<action android:name="com.katalysator.sdk.DID_RANGING" />
				<action android:name="com.katalysator.sdk.DID_MONITORING" />
			</intent-filter>
		</service>
		
		
If you want to use the built in full screen activity you also have to add:

		<activity android:name="com.katalysator.sdk.KATAdvertImageActivity">
		</activity>
	
	
The SDK requires these permissions:
	
  	<uses-permission android:name="android.permission.BLUETOOTH"/>
	<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
	<uses-permission android:name="android.permission.INTERNET"/>

At this moment the SDK depends on the gson library, which you can add as a maven 
dependency in gradle (as in the example project) or download the jar and add it as a dependency 
in your own project.

