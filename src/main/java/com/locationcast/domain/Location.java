package com.locationcast.domain;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Location")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum AttributeNames{
	   
	   id("id"),
	   point("point");
	   
	   public String name;
	   
	   private AttributeNames(String name) {
		this.name = name;
	   }
	   
	   public String getName(){
		   return this.name;
	   }
	   
	}

	@Id
	private long id ;

//	/@GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2D)
	private double[] point;

	@Transient
	private double latitute;
	@Transient
	private double logitute;

	public Location(){};
	
	public Location(double[]  location){
		this.id = System.currentTimeMillis();
		this.point = location;
	}
	
	public Location(long id, double[]  point){
		this.id = id;
		this.point = point;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public double getLatitute() {
		if (point != null) {
			this.latitute = point[1];
		}
		return latitute;
	}

	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}

	public double getLogitute() {
		if (point != null) {
			this.logitute = point[0];
		}
		return logitute;
	}

	public void setLogitute(double logitute) {
		this.logitute = logitute;
	}

	public void setLocation(double[] point) {
		this.point = point;
	}

	public double[] getLocation() {
		return point;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", location=" + Arrays.toString(point)+ "]";
	}

}
