Changelog
==========================

## Version 1.9.0

_2016-09-30_

 *  FIX: Update GPS fix using LocationServices.FusedLocationApi.getLastLocation when app comes to foreground
	FIX: Use "socket.getClass().getMethod("setHostname", String.class).invoke(socket, host);" fallback for TLS connections for < Android 4.2
	Skip reconnecting to GoogleApiClient if permissions are already satisfied

