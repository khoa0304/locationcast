/**
 * 
 */
package com.locationcast.factory;

import java.util.List;

import com.locationcast.domain.Topic;

/**
 * @author Khoa
 *
 */
public interface TopicFactory {

	public static enum TopicNames{
		
		COMMUNITY_WATCH("communityWatch","Community Watch"),
		REVIEWS("reviews","Reviews"),
		HANGE_OUT("hangOut","Hang Out"),
		SOCIAL_ENCOUNTER("socialEncounter","Social Encounter"),
		LOCAL_DEALS("localDeals","Local Deals");
		
		private String key;
		
		private String topicName;
		
		private TopicNames(String key, String name) {
		
			this.key = key;
			this.topicName = name;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param key the key to set
		 */
		public void setKey(String key) {
			this.key = key;
		}

		/**
		 * @return the topicName
		 */
		public String getTopicName() {
			return topicName;
		}

		/**
		 * @param topicName the topicName to set
		 */
		public void setTopicName(String topicName) {
			this.topicName = topicName;
		}
	}
	
	 List<Topic> getListOfAvailableTopic();
}
