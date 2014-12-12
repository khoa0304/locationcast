/**
 * 
 */
package com.locationcast.repository;

import java.util.List;

import com.locationcast.domain.Topic;

/**
 * @author Khoa
 *
 */
public interface TopicRepository {

	List<Topic> getAvailableTopicLits();
}
