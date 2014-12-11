package com.locationcast.domain;

import java.util.Arrays;

/**
 * 
 * @author Khoa
 * @since 0.1
 */

public class Location{

	public static enum AttributeNames{
	   
	   longAndLat("longitudeLatitude");
	   
	   public String name;
	   
	   private AttributeNames(String name) {
		this.name = name;
	   }
	   
	   public String getName(){
		   return this.name;
	   }
	   
	}

	private double[] longitudeLatitude;
	
	private double latitute;
	private double logitute;

	
	public Location(){};
	
	public Location(double[]  location){
		this.longitudeLatitude = location;
	}
	
	public Location(long id, double[]  point){
		this.longitudeLatitude = point;
	}

	public double getLatitute() {
		if (longitudeLatitude != null) {
			this.latitute = longitudeLatitude[1];
		}
		return latitute;
	}

	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}

	public double getLogitute() {
		if (longitudeLatitude != null) {
			this.logitute = longitudeLatitude[0];
		}
		return logitute;
	}

	public void setLogitute(double logitute) {
		this.logitute = logitute;
	}

	public void setLocation(double[] point) {
		this.longitudeLatitude = point;
	}

	public double[] getLocation() {
		return longitudeLatitude;
	}

	@Override
	public String toString() {
		return "Longitude And Latitude [=" + Arrays.toString(longitudeLatitude)+ "]";
	}

}
