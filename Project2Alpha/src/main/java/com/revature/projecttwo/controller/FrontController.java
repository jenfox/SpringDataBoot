package com.revature.projecttwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.projecttwo.controller.beans.Comment;
import com.revature.projecttwo.controller.beans.Notification;
import com.revature.projecttwo.controller.beans.Post;
import com.revature.projecttwo.controller.beans.Users;
import com.revature.projecttwo.controller.service.CommentService;
import com.revature.projecttwo.controller.service.NotificationService;
import com.revature.projecttwo.controller.service.PasswordService;
import com.revature.projecttwo.controller.service.PostService;
import com.revature.projecttwo.controller.service.UserService;
import com.revature.projecttwo.email.EmailServiceImpl;

@RestController
public class FrontController {

	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private EmailServiceImpl emailService;
	@Autowired
	private PasswordService passwordService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<Boolean> register(@RequestBody Users user) {
		System.out.println("Registering User:\n\t " + user);

		// TODO validation - i.e. all required user fields present

		userService.addUser(user);

		// Email
		emailService.sendSimpleMessage(user.getEmail(), "Register", "Hi, welcome to our social site test");

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Boolean> login(@RequestBody Users user) {
		System.out.println("Logging in User:\n\t " + user);

		// TODO Check

		Users userFound = userService.getUser(user.getEmail(), user.getPassword());
		if (userFound == null) {
			System.out.println("No user Found with email password combination");
			return ResponseEntity.ok(false);

		}
		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reset")
	public ResponseEntity<Boolean> resetPassword(@RequestBody String email) {
		System.out.println("Reset Password:\n\t " + email);

		// validate email
		Users user = userService.getUser(email);
		// no email exists -> return false
		if (user == null) {
			System.out.println("No matching Email");
			return ResponseEntity.ok(false);
		} else {
			// generate password
			String password = passwordService.generatePassword();
			// send email to reset
			emailService.sendSimpleMessage(email, "Password Reset", "Hi, your new password is " + password);
		}

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	public ResponseEntity<Users> getProfile(@PathVariable Integer id) {
		System.out.println("Getting user profile:\n\t " + id);

		Users user = userService.getUser(id);

		return ResponseEntity.ok(user);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{firstName}/{lastName}")
	public ResponseEntity<List<Users>> getUser(@PathVariable String firstName, @PathVariable String lastName) {

		System.out.println("Getting user:\n\t " + firstName + " " + lastName);

		List<Users> users = userService.getUsers(firstName, lastName);

		return ResponseEntity.ok(users);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public ResponseEntity<Boolean> updateProfile(@RequestBody Users user) {
		System.out.println("Updating User:\n\t " + user);

		// TODO Validation
		userService.addUser(user);

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

		postService.addPost(post);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/posts")
	public ResponseEntity<List<Post>> getFeed() {
		System.out.println("Getting Feed:\n\t ");

		List<Post> posts = postService.getAllPosts();

		return ResponseEntity.ok(posts);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/likes/{postId}")
	public ResponseEntity<Boolean> likePost(@PathVariable Integer postId) {
		System.out.println("Like Post:\n\t " + postId);

		// TODO get uid
		int userId = 0;
		postService.likePost(postId, userId);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/comments")
	public ResponseEntity<Boolean> comment(@RequestBody Comment comment) {
		System.out.println("Commenting:\n\t " + comment);

		commentService.addComment(comment);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/comments")
	public ResponseEntity<List<Comment>> getComments() {
		System.out.println("Getting comments:\n\t ");

		List<Comment> comments = commentService.getAllComments();

		return ResponseEntity.ok(comments);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/notifications")
	public ResponseEntity<List<Notification>> getNotifications() {
		System.out.println("Getting Notifications:\n\t ");

		List<Notification> notifications = notificationService.getAllNotification();

		return ResponseEntity.ok(notifications);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/notifications")
	public ResponseEntity<Boolean> addNotification(@RequestBody Notification notification) {
		System.out.println("Adding notification:\n\t " + notification);

		notificationService.addNotification(notification);

		return ResponseEntity.ok(true);
	}

}
