/**
 * 
 */
package com.locationcast.util;

/**
 * @author Khoa
 *
 */
public class GeoLocationConstant {

	
	/**
	 * 
	 * decimal  degrees    distance places
		-------------------------------  
		0        1.0        111 km
		1        0.1        11.1 km
		2        0.01       1.11 km
		3        0.001      111 m
		4        0.0001     11.1 m
		5        0.00001    1.11 m
		6        0.000001   0.111 m
		7        0.0000001  1.11 cm
		8        0.00000001 1.11 mm
		
		
		Decimal Places   Aprox. Distance    Say What?
		1                10 kilometers      6.2 miles
		2                1 kilometer        0.62 miles
		3                100 meters         About 328 feet
		4                10 meters          About 33 feet
		5                1 meter            About 3 feet
		6                10 centimeters     About 4 inches
		7                1.0 centimeter     About ½ an inch
		8                1.0 millimeter     The width of paperclip wire.
	 */
	
	public static final Double KILOMETER_PER_DEGREE = 111.0d;

	public static final Double MILE_PER_DEGREE = 69.0d;
	
	public static final double  defaultMaxConversationDistance = 0.001;
	public static final double  defaultMinConversationDistance = 0.0001;
	public static final double  precisedConversationDistance = 0.00001 ;
	
	
	public static Double getInMile(Double maxdistance) {
		return maxdistance / MILE_PER_DEGREE;
	}
}
