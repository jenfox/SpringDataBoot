package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.dtos.UserDto;
import com.revature.projecttwo.container.repo.UserRepo;
import com.revature.projecttwo.container.validation.UserValidService;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserValidService userValidation;

	public List<Resident> getAllUsers() {
		System.out.println("Getting all users:\n\t");
		List<Resident> users = new ArrayList<>();
		// method reference add method call
		userRepo.findAll().forEach(users::add);

		return users;
	}

	public List<UserDto> getUsers(String firstName, String lastName) {
		System.out.println("Getting all users matching:\n\t" + firstName + " " + lastName);
		List<UserDto> usersDtos = new ArrayList<>();
		// method reference add method call
		List<Resident> users = userRepo.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);

		// turn into DTOs to restrict info returned
		for (Resident u : users) {
			usersDtos.add(new UserDto(u.getId(), u.getFirstName(), u.getLastName()));
		}
		return usersDtos;
	}

	public List<UserDto> getUsersDtosMatching(String name) {
		System.out.println("Getting all users matching name:\n\t" + name);
		List<UserDto> usersDtos = new ArrayList<>();

		// regex it
		name = '%' + name + '%';

		List<Resident> users = userRepo.findByFirstNameLikeOrLastNameLikeIgnoreCase(name, name);

		// turn into DTOs to restrict info returned
		for (Resident u : users) {
			usersDtos.add(new UserDto(u.getId(), u.getFirstName(), u.getLastName()));
		}

		return usersDtos;
	}

	public Resident getUser(Integer id) {
		System.out.println("Found User in DB:\n\t" + id);
		Optional<Resident> user = userRepo.findById(id);

		return user.get();
	}

	public Resident getUser(String email, String password) {
		System.out.println("Found User in DB:\n\t" + email + " " + password);

		Resident user = userRepo.getByEmail(email);

		// check password encypt match
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			System.out.println("Password matches email");
			return user;
		} else {
			System.out.println("Password does not match email");
			return null;
		}
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
		if (userValidation.checkPassword(user.getPassword())) {
			System.out.println("Saving User to DB:\n\t" + user);

			// encode the user password before storing it into db
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			System.out.println("Password Encryption = " + user.getPassword());

			return userRepo.save(user);
		} else {
			System.out.println("No valid password found:\n\t" + user);
			return null;
		}
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
	
	public void updateUserImage(String path, int userId) {
		System.out.println("Updating user picture in DB:+\n\t" + userId + " " + path);
		Resident user = userRepo.getById(userId);
		user.setProfileUrl(path);
		userRepo.save(user);
	}
}
