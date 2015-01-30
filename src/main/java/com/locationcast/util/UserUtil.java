package com.locationcast.util;

import com.locationcast.domain.Poster;

public class UserUtil {


	public static Poster getAnonymousPoster(double[] longitudeAndLatitude, String ipAddress){
		Poster poster = new Poster();
		
		String geoString = GeoLocationUtil.convertGeoLocationToString(longitudeAndLatitude);
		poster.setAliasUserName(geoString);
		poster.setIpAddress(ipAddress);
		
		return poster;
	}
	
	public static Poster getPoster(String aliasName, long id){
		Poster poster = new Poster();
		poster.setAliasUserName(aliasName);
		poster.setUserId(id);
		return poster;
	}
}

