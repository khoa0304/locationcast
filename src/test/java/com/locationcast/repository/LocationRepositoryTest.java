package com.locationcast.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.DefaultIndexOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.locationcast.domain.Location;
import com.locationcast.mongodb.config.MongoConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfig.class })
public class LocationRepositoryTest extends AbstractMongoDBReposTest{

	// @Rule
	// public MongoDbRule mongoDbRule =
	// newMongoDbRule().defaultSpringMongoDb("demo-test");

	// nosql-unit requirement
	

	public static final Double KILOMETER_PER_DEGREE = 111.0d;

	public static final Double MILE_PER_DEGREE = 69.0d;

	@BeforeClass
	public void init() {

		DefaultIndexOperations indexOp = new DefaultIndexOperations(
				mongoOperation, Location.class.getSimpleName().toLowerCase());
		indexOp.dropAllIndexes();

		mongoOperation.dropCollection(Location.class);
		mongoOperation.createCollection(Location.class);

		GeospatialIndex geoIndex = new GeospatialIndex(
				Location.AttributeNames.longAndLat.getName());
		geoIndex.typed(GeoSpatialIndexType.GEO_2D);
		indexOp.ensureIndex(geoIndex);

		double[][] longLatArray = getLongitudeLatitudeDataset(IncrementFactor);
		List<Location> longLatArrayLits = new ArrayList<Location>(
				longLatArray.length);
		int i = 1;
		int length = longLatArray.length;
		int newSize = length - 1;

		for (int actualSize = 0; actualSize < length && i < newSize; actualSize++, i++) {
			double[] coordiate = longLatArray[actualSize];

			Location location = new Location(i, coordiate);
			longLatArrayLits.add(location);
		}
		mongoOperation.insertAll(longLatArrayLits);

		longLatArray = getLongitudeLatitudeDataset(DecrementFactor);
		longLatArrayLits = new ArrayList<Location>(longLatArray.length);

		length = longLatArray.length;
		newSize = length + i - 1;

		for (int actualSize = 0; actualSize < length && i < newSize; actualSize++, i++) {

			double[] coordiate = longLatArray[actualSize];

			Location location = new Location(i, coordiate);
			longLatArrayLits.add(location);
		}
		mongoOperation.insertAll(longLatArrayLits);


	}

	@Test
	public void testQueryForMaxDistanceInMile() {

		Criteria criteria = new Criteria("point").near(
				new Point(startingLongitude, startingLatitude)).maxDistance(
				getInMile(0.0001));
		Query query = new Query(criteria);
		
		List<Location> locationList = mongoOperation
				.find(query, Location.class);
		System.out.println("Total location : " + locationList.size());
		for (Location location : locationList) {
			System.out.println(location);
		}
		
		assertEquals(4, locationList.size());
	}

	
	@Test
	public void testLocationAndTopicQueries(){
		
		
	}
	
	
	private Double getInMile(Double maxdistance) {
		return maxdistance / MILE_PER_DEGREE;
	}

	static double startingLatitude = 37.319337; // 37.999999
												// 37.319337,-121.839370
	static double startingLongitude = -121.839370; // -121.800000 to
													// -122.8000000
	static double DecrementFactor = -0.000001;
	static double IncrementFactor = 0.000001;

	static int numberOfCoordinate = 10000;

	final static DecimalFormat form = new DecimalFormat("#.######");

	// 37.300000 37.5

	@DataProvider(name = "longitudeAndLatitude")
	public static double[][] getLongitudeLatitudeDataset(double fraction) {
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
