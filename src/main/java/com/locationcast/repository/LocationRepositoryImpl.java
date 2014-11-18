package com.locationcast.repository;


import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.IndexOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Repository;

import com.locationcast.domain.Location;


/**
 * 
 * @author Khoa
 *
 */
@Repository
public class LocationRepositoryImpl implements LocationRepository {

    static final Logger logger = LoggerFactory.getLogger(LocationRepositoryImpl.class);

    private static final String COLLECTION = "location";
  
    @Autowired
    @Qualifier("locationTemplate")
    MongoTemplate mongoTemplate;
    

  
	public Location insertLocaiton(double longitude, double latitude) {
	
		logger.info(String.format("Insert Longitutde %f Latitude %f", longitude,latitude));
		return null;
	}
	
	public List<Location> getLocation(){
		List<Location> locationList = mongoTemplate.findAll(Location.class);
		return locationList;
	}

//    @Autowired
//    @Qualifier("tracksTemplate")
//    private MongoOperations mongoOps;
    
    
  
}
