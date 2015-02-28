package com.locationcast.lab;

import java.text.DecimalFormat;

import org.testng.annotations.Test;

public class RandomLab {

	@Test
	public void testLongitudeDecimalIncrement() {
		double x = -121.839370;
		DecimalFormat form = new DecimalFormat("#.######");
		while (x < 100000.0) {
			x = x + 0.000001;
			System.out.println("X : " + Double.valueOf(form.format(x)));

		}
	}
	
	@Test
	public void testLatitudeDecimalIncrement() {
		double x = 37.319337;
		DecimalFormat form = new DecimalFormat("#.######");
		while (x < 100.0) {
			x = x + 0.000001;
			System.out.println("X : " + Double.valueOf(form.format(x)));

		}
	}

}
