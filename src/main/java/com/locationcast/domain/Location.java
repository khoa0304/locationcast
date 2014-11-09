package com.locationcast.domain;

import java.io.Serializable;

public class Location implements Serializable {
    
 
	private static final long serialVersionUID = 1L;
	private Double longitude;
    private Double latitude;
    
    public Location( Double longitute, Double latitude) {
		this.longitude = longitute;
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
 
  
}
