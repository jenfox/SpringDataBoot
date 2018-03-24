package com.revature.projecttwo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.projecttwo.controller.beans.Comment;
import com.revature.projecttwo.controller.beans.Notification;
import com.revature.projecttwo.controller.beans.Post;
import com.revature.projecttwo.controller.beans.User;

@RestController
public class FrontController {

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<Boolean> register(@RequestBody User user) {
		System.out.println("Registering User:\n\t " + user);

		// TODO validation - i.e. all required user fields present

		// TODO DAO
		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Boolean> login(@RequestBody User user) {
		System.out.println("Logging in User:\n\t " + user);

		// TODO Check

		// TODO authentication
		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reset")
	public ResponseEntity<Boolean> resetPassword(@RequestBody String email) {
		System.out.println("Reset Password:\n\t " + email);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getProfile(@PathVariable int id) {
		System.out.println("Getting user profile:\n\t " + id);

		// dummy data
		User user = new User();
		user.setFirstName("Bobbi");
		user.setLastName("Tables");
		user.setId(id);

		return ResponseEntity.ok(user);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{firstName}/{lastName}")
	public ResponseEntity<User> getUser(@PathVariable String firstName, @PathVariable String lastName) {

		System.out.println("Getting user:\n\t " + firstName + " " + lastName);

		// TODO fetch

		// dummy data
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setId(111);

		return ResponseEntity.ok(user);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public ResponseEntity<Boolean> updateProfile(@RequestBody User user) {
		System.out.println("Updating User:\n\t " + user);

		// TODO Validation

		// TODO DAO

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/profilePictures")
	public ResponseEntity<Boolean> updateProfilePic(@RequestBody String url) {
		System.out.println("Update Profile Pic:\n\t " + url);

		// TODO S3

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/posts")
	public ResponseEntity<Boolean> savePost(@RequestBody Post post) {
		System.out.println("Saving Post:\n\t " + post);

		// TODO validate

		// TODO DAO

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/posts")
	public ResponseEntity<List<Post>> getFeed() {
		System.out.println("Getting Feed:\n\t ");

		// TODO DAO
		ArrayList<Post> posts = new ArrayList<Post>();
		posts.add(new Post(1, null, "Content1", null, null, 10));
		posts.add(new Post(2, null, "Content2", null, null, 20));

		return ResponseEntity.ok(posts);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/likes/{postId}")
	public ResponseEntity<Boolean> likePost(@PathVariable String postId) {
		System.out.println("Like Post:\n\t " + postId);

		// TODO DAO

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/comments")
	public ResponseEntity<Boolean> comment(@RequestBody Comment comment) {
		System.out.println("Commenting:\n\t " + comment);

		// TODO DAO

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/comments")
	public ResponseEntity<List<Comment>> getComments() {
		System.out.println("Getting comments:\n\t ");

		// TODO DAO
		ArrayList<Comment> comments = new ArrayList<>();
		comments.add(new Comment(1, 10, 1, 1, "Content1"));
		comments.add(new Comment(2, 20, 2, 2, "Content2"));

		return ResponseEntity.ok(comments);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/notifications")
	public ResponseEntity<List<Notification>> getNotifications() {
		System.out.println("Getting Notifications:\n\t ");
		// TODO DAO
		ArrayList<Notification> notifications = new ArrayList<>();
		notifications.add(new Notification(1, 10, "Content1", null));
		notifications.add(new Notification(2, 20, "Content2", null));

		return ResponseEntity.ok(notifications);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/notifications")
	public ResponseEntity<Boolean> addNotification(@RequestBody Notification notification) {
		System.out.println("Adding notification:\n\t " + notification);

		// TODO DAO

		return ResponseEntity.ok(true);
	}

}
