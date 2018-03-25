package com.revature.projecttwo.controller.repo;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.controller.beans.Comment;

public interface CommentRepo extends CrudRepository<Comment, Integer> {

}
