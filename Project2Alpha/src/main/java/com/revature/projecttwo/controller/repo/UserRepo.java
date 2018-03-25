package com.revature.projecttwo.controller.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.controller.beans.Users;

public interface UserRepo extends CrudRepository<Users, Integer> {

	Users getByEmailAndPassword(String email, String password);

	Users getByEmail(String email);

	List<Users> getByFirstNameAndLastName(String firstName, String lastName);

}
