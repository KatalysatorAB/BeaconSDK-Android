Changelog
==========================

## Version 1.9.9

_2017-01-03_

 *  FIX: Issue that caused a crash when scanning for beacons in background on very few devices running Android 6


## Version 1.9.8

_2016-12-19_

 *  FIX: Issue that caused ```getAudiencesAndGeotags()``` not to initiate request if advertisting identifier null and location permission not given
 * 	Added method to get local cached tags from storage
 *	Added error callback that gets called on HTTP problems


## Version 1.9.7

_2016-11-17_

 *  FIX: Fix or catch some null pointer and runtime exceptions, mostly BLE related


## Version 1.9.6

_2016-11-16_

 *  Breaking Change: KATManager is now a singleton, calling ```init()``` is required
 *  Breaking Change: It is required to set the ```setAudiencesAndGeotagsCallback()``` callback, then get tags using ```KATManager.getInstance().getAudiencesAndGeotags()```
 *  Added init param ```tagCacheExpireSeconds``` to set the caching time for tags


## Version 1.9.3

_2016-10-13_

 *  Added flag ```ipOnly``` in constructor in case the app implements a more complex permission layer
 

## Version 1.9.2

_2016-10-01_

 *  FIX: ```startMonitoring``` or ```getAudiencesAndGeotags``` calls when GoogleApiClient connection slow
 *  Improved behaviour on some strictmodes disk read violations


## Version 1.9.1

_2016-09-30_

 *  Skip devices < 4.2 for when calling ```startMonitoring``` or ```getAudiencesAndGeotags```
 *  Improved behaviour on some strictmodes disk read violations
 *  Removed SSL pinning option
 * 	Skip callback ```availableAudiencesUpdated``` of mapping is empty


## Version 1.9.0

_2016-09-30_

 *  FIX: Update GPS fix using LocationServices.FusedLocationApi.getLastLocation when app comes to foreground
 *  FIX: Use "socket.getClass().getMethod("setHostname", String.class).invoke(socket, host);" fallback for TLS connections for < Android 4.2
 *  Skip reconnecting to GoogleApiClient if permissions are already satisfied
