package com.revature.projecttwo.container.repo;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.container.beans.Comment;

public interface CommentRepo extends CrudRepository<Comment, Integer> {

}
