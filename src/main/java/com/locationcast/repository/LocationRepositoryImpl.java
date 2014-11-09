package com.locationcast.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    @Autowired
    MongoTemplate mongoTemplate;

	public void insertLocaiton(double longitude, double latitude) {
	 
		logger.info(String.format("Insert Longitutde %f Latitude %f", longitude,latitude));
		mongoTemplate.insert(new Location(longitude,latitude));
		
	}

//    @Autowired
//    @Qualifier("tracksTemplate")
//    private MongoOperations mongoOps;
    
    
  
}
