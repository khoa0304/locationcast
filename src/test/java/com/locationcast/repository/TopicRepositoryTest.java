/**
 * 
 */
package com.locationcast.repository;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.locationcast.domain.Topic;
import com.locationcast.factory.TopicFactory;

/**
 * @author Khoa
 *
 */
public class TopicRepositoryTest  extends AbstractMongoDBReposTest {

	@Autowired
	TopicRepository topicRepos;
	
	@Test
	public void testGetAllAvailableTopicList(){
		List<Topic> topicList = topicRepos.getAvailableTopicLits();
		assertEquals(topicList.size(),TopicFactory.TopicNames.values().length);
	}
}
