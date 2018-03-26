package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public List<Resident> getAllUsers() {
		System.out.println("Getting all users:\n\t");
		List<Resident> users = new ArrayList<>();
		// method reference add method call
		userRepo.findAll().forEach(users::add);

		return users;
	}

	public List<Resident> getUsers(String firstName, String lastName) {
		System.out.println("Getting all users matching:\n\t" + firstName + " " + lastName);
		List<Resident> users = new ArrayList<>();
		// method reference add method call
		userRepo.getByFirstNameAndLastNameIgnoreCase(firstName, lastName).forEach(users::add);

		return users;
	}

	public Resident getUser(Integer id) {
		System.out.println("Found User in DB:\n\t" + id);
		Optional<Resident> user = userRepo.findById(id);

		return user.get();
	}

	public Resident getUser(String email, String password) {
		System.out.println("Found User in DB:\n\t" + email + " " + password);
		Resident user = userRepo.getByEmailAndPassword(email, password);

		return user;
	}

	public Resident getUser(String email) {
		System.out.println("Found User in DB:\n\t" + email);
		return userRepo.getByEmail(email);
	}

	public void addUser(Resident user) {
		System.out.println("Saving User to DB:\n\t" + user);
		userRepo.save(user);
	}

	public void updateUser(Resident user, String id) {
		System.out.println("Updating User to DB:\n\t" + user);
		userRepo.save(user);
	}

	public void deleteUser(Integer id) {
		System.out.println("Deleting User to DB:+\n\t" + id);
		userRepo.deleteById(id);
	}
}
