package com.revature.projecttwo.topic;

import org.springframework.data.repository.CrudRepository;

//Data service class
//CrudRepository: contains logic for any entity class <Class, Id type>
public interface TopicRepo extends CrudRepository<Topic, String> {

	// List<Topic> getAllTopics();
	//
	// Topic getTopic(String id);
	//
	// void updateTopic(Topic t);
	//
	// void deleteTopic(String id);
}
