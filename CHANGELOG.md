Changelog
==========================

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
