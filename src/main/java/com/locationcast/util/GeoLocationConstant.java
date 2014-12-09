/**
 * 
 */
package com.locationcast.util;

/**
 * @author Khoa
 *
 */
public class GeoLocationConstant {

	public static final Double KILOMETER_PER_DEGREE = 111.0d;

	public static final Double MILE_PER_DEGREE = 69.0d;
	
	public static Double getInMile(Double maxdistance) {
		return maxdistance / MILE_PER_DEGREE;
	}
}
