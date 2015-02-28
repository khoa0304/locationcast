/**
 * 
 */
package com.locationcast.test.data;

import java.text.DecimalFormat;

/**
 * @author Khoa
 *
 */
public class LocationTestData {

	
	public static double startingLongitude = -121.839370;
	public static double startingLatitude = 37.319337;
	
	

	final static DecimalFormat form = new DecimalFormat("#.######");

	
	
	public static double[] getHomeLongitudeAndLatitude(){
		return new double[]{startingLongitude,startingLatitude};
	}

	
	public static double[][] getLongitudeLatitudeDataset(int numberOfCoordinate,double fraction) {
		double startingLatitude = 37.319337;
		double startingLongitude = -121.839370;

		double[][] longitutudeAndLatitudeArray = new double[numberOfCoordinate][];

		for (int i = 0; i < numberOfCoordinate; i++) {

			double[] coordinate = { startingLongitude, startingLatitude };

			startingLongitude = Double.valueOf(form.format(startingLongitude
					+ fraction));
			startingLatitude = Double.valueOf(form.format(startingLatitude
					+ fraction));

			longitutudeAndLatitudeArray[i] = coordinate;

		}

		return longitutudeAndLatitudeArray;
	}
}
