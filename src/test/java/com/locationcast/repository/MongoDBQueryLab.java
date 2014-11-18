package com.locationcast.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.locationcast.mongodb.config.MongoConfig;

public class MongoDBQueryLab {

	@Autowired
	private ApplicationContext applicationContext;

	MongoOperations mongoOperation = null;

	@BeforeSuite
	public void init() {
		applicationContext = new AnnotationConfigApplicationContext(
				MongoConfig.class);
		mongoOperation = (MongoOperations) applicationContext
				.getBean("testTemplateDB");
	}

	@Test
	public void testMongodbQueries() {

		// insert 6 users for testing
		List<User> users = new ArrayList<User>();

		User user1 = new User("1001", "ant", 10);
		User user2 = new User("1002", "bird", 20);
		User user3 = new User("1003", "cat", 30);
		User user4 = new User("1004", "dog", 40);
		User user5 = new User("1005", "elephant", 50);
		User user6 = new User("1006", "frog", 60);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		mongoOperation.insert(users, User.class);

		System.out.println("Case 1 - find with BasicQuery example");

		BasicQuery query1 = new BasicQuery(
				"{ age : { $lt : 40 }, name : 'cat' }");
		User userTest1 = mongoOperation.findOne(query1, User.class);

		System.out.println("query1 - " + query1.toString());
		System.out.println("userTest1 - " + userTest1);

		System.out.println("\nCase 2 - find example");

		Query query2 = new Query();
		query2.addCriteria(Criteria.where("name").is("dog").and("age").is(40));

		User userTest2 = mongoOperation.findOne(query2, User.class);
		System.out.println("query2 - " + query2.toString());
		System.out.println("userTest2 - " + userTest2);

		System.out.println("\nCase 3 - find list $inc example");

		List<Integer> listOfAge = new ArrayList<Integer>();
		listOfAge.add(10);
		listOfAge.add(30);
		listOfAge.add(40);

		Query query3 = new Query();
		query3.addCriteria(Criteria.where("age").in(listOfAge));

		List<User> userTest3 = mongoOperation.find(query3, User.class);
		System.out.println("query3 - " + query3.toString());

		for (User user : userTest3) {
			System.out.println("userTest3 - " + user);
		}

		System.out.println("\nCase 4 - find list $and $lt, $gt example");

		Query query4 = new Query();

		// it hits error
		// query4.addCriteria(Criteria.where("age").lt(40).and("age").gt(10));

		query4.addCriteria(Criteria
				.where("age")
				.exists(true)
				.andOperator(Criteria.where("age").gt(10),
						Criteria.where("age").lt(40)));

		List<User> userTest4 = mongoOperation.find(query4, User.class);
		System.out.println("query4 - " + query4.toString());

		for (User user : userTest4) {
			System.out.println("userTest4 - " + user);
		}

		System.out.println("\nCase 5 - find list and sorting example");
		Query query5 = new Query();
		query5.addCriteria(Criteria.where("age").gte(30));
		query5.with(new Sort(Sort.Direction.DESC, "age"));

		List<User> userTest5 = mongoOperation.find(query5, User.class);
		System.out.println("query5 - " + query5.toString());

		for (User user : userTest5) {
			System.out.println("userTest5 - " + user);
		}

		System.out.println("\nCase 6 - find by regex example");
		Query query6 = new Query();
		query6.addCriteria(Criteria.where("name").regex("D.*G", "i"));

		List<User> userTest6 = mongoOperation.find(query6, User.class);
		System.out.println("query6 - " + query6.toString());

		for (User user : userTest6) {
			System.out.println("userTest6 - " + user);
		}

		mongoOperation.dropCollection(User.class);

	}
	
	@Document(collection = "users")
	public class User {
	 
		@Id
		private String id;
	 
		@Indexed
		private String ic;
	 
		private String name;	
	 
		private int age;
		
		public User(String ic, String name, int age) {
			this.ic = ic;
			this.name = name;
			this.age = age;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the ic
		 */
		public String getIc() {
			return ic;
		}

		/**
		 * @param ic the ic to set
		 */
		public void setIc(String ic) {
			this.ic = ic;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the age
		 */
		public int getAge() {
			return age;
		}

		/**
		 * @param age the age to set
		 */
		public void setAge(int age) {
			this.age = age;
		}
	 
		public String toString(){
			return String.format(" User id: %d  - name: %s - age: %d", id,name,age);
		}
	 
	}

}
