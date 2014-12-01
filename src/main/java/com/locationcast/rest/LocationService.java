package com.locationcast.rest;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locationcast.domain.Location;
import com.locationcast.repository.LocationRepository;

@Controller
@RequestMapping("/rest/location")
@Secured("ROLE_USER")
public class LocationService {

	 
	private static final Logger logger = LoggerFactory.getLogger(LocationService.class);
    
	public static final Double KILOMETER = 111.0d;

    @Autowired
    LocationRepository locationRepos;
    
    @RequestMapping(value = "/getlocation", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public    List<Location> getByLocation() throws Exception {
        List<Location> list = locationRepos.getLocation();
        return list;
    }
    
    
    @RequestMapping(value = "/add/{lon}/{lat}", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public Location insertLocation(@PathVariable("lon") Double longitute, @PathVariable("lat") Double latitude) throws Exception {
    	
        Location location = locationRepos.insertLocaiton(longitute, latitude);
        return location;
    }
    
    
    
}
