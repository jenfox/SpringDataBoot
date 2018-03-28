package com.revature.projecttwo.container.repo;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.container.beans.Consideration;

public interface CommentRepo extends CrudRepository<Consideration, Integer> {

}
