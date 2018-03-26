package com.revature.projecttwo.container.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.container.beans.Post;

public interface PostRepo extends CrudRepository<Post, Integer> {

	List<Post> findTop20ByOrderByDateCreatedDesc();

	// List<Post> findTop20ByGreaterThanDateCreatedOrderByDateCreated(Date
	// datecreated);
}