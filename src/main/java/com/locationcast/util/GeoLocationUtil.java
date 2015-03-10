package com.locationcast.util;

import com.locationcast.domain.Location;

public class GeoLocationUtil {

	private static final String geoLocationStringFormat = "Longitude:%f - Latitude: %f";
	
	public static String convertGeoLocationToString(double[] longitudeAndLatitude){
		
		String st = "";
		if(longitudeAndLatitude.length == 2){
			st =  String.format(geoLocationStringFormat, longitudeAndLatitude[0],longitudeAndLatitude[1]);
		}
		
		else{
			st = String.format(geoLocationStringFormat, 0, 0);
		}
		
		return st;
	}
	

	public static Location getLocation(double[] longAndLat) {
		return new Location(longAndLat);
	}

}
