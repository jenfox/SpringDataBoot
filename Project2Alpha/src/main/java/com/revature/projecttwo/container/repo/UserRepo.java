package com.revature.projecttwo.container.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.container.beans.Resident;

public interface UserRepo extends CrudRepository<Resident, Integer> {

	Resident getByEmailAndPassword(String email, String password);

	Resident getByEmail(String email);

	Resident getById(Integer Id);

	List<Resident> findByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

	List<Resident> findByFirstNameLikeOrLastNameLikeIgnoreCase(String firstName, String lastName);

}
