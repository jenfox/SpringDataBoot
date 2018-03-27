package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	public Resident registerNewUserAccount(Resident user) {// throws EmailExistsException {
		// if (emailExist(accountDto.getEmail())) {
		// throw new EmailExistsException("There is an account with that email adress:"
		// + accountDto.getEmail());
		// }
		System.out.println("Saving User to DB:\n\t" + user);

		// encode the user password before storing it into db
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		System.out.println("Password Encryption = " + user.getPassword());

		return userRepo.save(user);
	}

	public void updateUser(Resident user) {
		System.out.println("Updating User in DB:\n\t" + user);

		// check if password changed? and reencode
		// TODO
		// user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}

	public void deleteUser(Integer id) {
		System.out.println("Deleting User to DB:+\n\t" + id);
		userRepo.deleteById(id);
	}
}
