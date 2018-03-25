package com.revature.projecttwo.courses;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//CrudRepository: contains logic for any entity class <Class, Id type>
public interface CourseRepo extends CrudRepository<Course, String> {

	// gets all courses based on name
	// FORMAT: findByProperty
	public List<Course> findByName(String name);

	public List<Course> findByDescription(String description);

	// referring to topic property course and the id property of topic obj
	public List<Course> findByTopicId(String topicId);
}
