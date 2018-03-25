package com.revature.projecttwo.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.controller.beans.Users;
import com.revature.projecttwo.controller.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public List<Users> getAllUsers() {
		System.out.println("Getting all users:\n\t");
		List<Users> users = new ArrayList<>();
		// method reference add method call
		userRepo.findAll().forEach(users::add);

		return users;
	}

	public List<Users> getUsers(String firstName, String lastName) {
		System.out.println("Getting all users matching:\n\t" + firstName + " " + lastName);
		List<Users> users = new ArrayList<>();
		// method reference add method call
		userRepo.getByFirstNameAndLastName(firstName, lastName).forEach(users::add);

		return users;
	}

	public Users getUser(Integer id) {
		System.out.println("Found User in DB:\n\t" + id);
		Optional<Users> user = userRepo.findById(id);

		return user.get();
	}

	public Users getUser(String email, String password) {
		System.out.println("Found User in DB:\n\t" + email + " " + password);
		Users user = userRepo.getByEmailAndPassword(email, password);

		return user;
	}

	public Users getUser(String email) {
		System.out.println("Found User in DB:\n\t" + email);
		return userRepo.getByEmail(email);
	}

	public void addUser(Users user) {
		System.out.println("Saving User to DB:\n\t" + user);
		userRepo.save(user);
	}

	public void updateUser(Users user, String id) {
		System.out.println("Updating User to DB:\n\t" + user);
		userRepo.save(user);
	}

	public void deleteUser(Integer id) {
		System.out.println("Deleting User to DB:+\n\t" + id);
		userRepo.deleteById(id);
	}
}
