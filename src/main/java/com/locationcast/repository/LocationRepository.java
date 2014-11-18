package com.locationcast.repository;

import java.util.List;

import com.locationcast.domain.Location;

public interface LocationRepository {

	Location insertLocaiton(double longitude, double latitude);
	List<Location> getLocation();
}
