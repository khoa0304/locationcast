package com.locationcast.repository.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.Location;
import com.locationcast.repository.LocationRepository;


/**
 * 
 * @author Khoa
 *
 */
@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private static final Logger logger = LoggerFactory.getLogger(LocationRepositoryImpl.class);

  
    @Autowired
    @Qualifier("locationcastdb")
    MongoOperations mongoOperation;
    

  
	public Location insertLocaiton(double longitude, double latitude) {
	
		logger.info(String.format("Insert Longitutde %f Latitude %f", longitude,latitude));
		return null;
	}
	
	public List<Location> getLocation(){
		List<Location> locationList = mongoOperation.findAll(Location.class);
		return locationList;
	}

//    @Autowired
//    @Qualifier("tracksTemplate")
//    private MongoOperations mongoOps;
    
    
  
}
