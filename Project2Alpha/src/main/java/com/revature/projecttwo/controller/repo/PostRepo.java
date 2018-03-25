package com.revature.projecttwo.controller.repo;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.controller.beans.Post;

public interface PostRepo extends CrudRepository<Post, Integer> {

}
