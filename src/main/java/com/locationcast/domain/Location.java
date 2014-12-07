package com.locationcast.domain;

import java.util.Arrays;

/**
 * 
 * @author Khoa
 * @since 0.1
 */

public class Location{

	public static enum AttributeNames{
	   
	   longAndLat("longAndLat");
	   
	   public String name;
	   
	   private AttributeNames(String name) {
		this.name = name;
	   }
	   
	   public String getName(){
		   return this.name;
	   }
	   
	}

	private double[] longAndLat;
	
	private double latitute;
	private double logitute;

	
	public Location(){};
	
	public Location(double[]  location){
		this.longAndLat = location;
	}
	
	public Location(long id, double[]  point){
		this.longAndLat = point;
	}

	public double getLatitute() {
		if (longAndLat != null) {
			this.latitute = longAndLat[1];
		}
		return latitute;
	}

	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}

	public double getLogitute() {
		if (longAndLat != null) {
			this.logitute = longAndLat[0];
		}
		return logitute;
	}

	public void setLogitute(double logitute) {
		this.logitute = logitute;
	}

	public void setLocation(double[] point) {
		this.longAndLat = point;
	}

	public double[] getLocation() {
		return longAndLat;
	}

	@Override
	public String toString() {
		return "Longitude And Latitude [=" + Arrays.toString(longAndLat)+ "]";
	}

}
