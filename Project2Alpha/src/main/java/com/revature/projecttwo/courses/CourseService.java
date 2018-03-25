package com.revature.projecttwo.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;

	public List<Course> getAllCourses(String topicId) {
		List<Course> topics = new ArrayList<>();
		// method reference add method call
		courseRepo.findByTopicId(topicId).forEach(topics::add);

		return topics;
	}

	public Course getCourse(String id) {
		Optional<Course> topic = courseRepo.findById(id);

		return topic.get();
	}

	public void addCourse(Course topic) {
		courseRepo.save(topic);
	}

	public void updateCourse(Course topic, String id) {
		courseRepo.save(topic);
	}

	public void deleteCourse(String id) {
		courseRepo.deleteById(id);
	}
}
