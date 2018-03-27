package com.revature.projecttwo.container;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.projecttwo.container.beans.Comment;
import com.revature.projecttwo.container.beans.Notification;
import com.revature.projecttwo.container.beans.Post;
import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.service.CommentService;
import com.revature.projecttwo.container.service.NotificationService;
import com.revature.projecttwo.container.service.PostService;
import com.revature.projecttwo.container.service.UserService;
import com.revature.projecttwo.email.EmailServiceImpl;

@CrossOrigin
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

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<Boolean> register(@RequestBody Resident user) {
		System.out.println("Registering User:\n\t " + user);

		// TODO validation - i.e. all required user fields present

		userService.registerNewUserAccount(user);

		// Email Registered
		emailService.sendRegister(user.getEmail(), user.getPassword());

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Resident> login(@RequestBody Resident user) {
		System.out.println("Logging in User:\n\t " + user);

		Resident userFound = userService.getUser(user.getEmail(), user.getPassword());
		if (userFound == null) {
			System.out.println("No user Found with email password combination");
			return ResponseEntity.ok(null);

		} else {
			// User exists and matches email/password in DB
			// TODO Authenticate
		}
		return ResponseEntity.ok(userFound);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reset")
	public ResponseEntity<Boolean> resetPassword(@RequestBody Resident userSkeleton) {
		System.out.println("Reset Password:\n\t " + userSkeleton.getEmail());

		// validate email
		Resident user = userService.getUser(userSkeleton.getEmail());
		// no email exists -> return false
		if (user == null) {
			System.out.println("No matching Email");
			return ResponseEntity.ok(false);
		} else {
			// send email to reset
			emailService.sendReset(user.getEmail());
		}

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	public ResponseEntity<Resident> getProfile(@PathVariable Integer id) {
		System.out.println("Getting user profile:\n\t " + id);

		Resident user = userService.getUser(id);

		return ResponseEntity.ok(user);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{firstName}/{lastName}")
	public ResponseEntity<List<Resident>> getUser(@PathVariable String firstName, @PathVariable String lastName) {

		System.out.println("Getting user:\n\t " + firstName + " " + lastName);

		List<Resident> users = userService.getUsers(firstName, lastName);

		return ResponseEntity.ok(users);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public ResponseEntity<Boolean> updateProfile(@RequestBody Resident user) {
		System.out.println("Updating User:\n\t " + user);

		// TODO Validation
		userService.updateUser(user);

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
		System.out.println("Getting All Posts:\n\t ");

		List<Post> posts = postService.getAllPosts();

		return ResponseEntity.ok(posts);
	}

	/**
	 * NEW GETS NEWEST 20 POSTS
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/posts/feed")
	public ResponseEntity<List<Post>> getLimitedFeed() {
		System.out.println("Getting Feed:\n\t ");

		List<Post> posts = postService.getPrevious20Posts();

		return ResponseEntity.ok(posts);
	}

	// /**
	// * NEW 20 POSTS PAST DATE
	// *
	// * @return
	// */
	// @RequestMapping(method = RequestMethod.GET, value = "/posts/feed/{date}")
	// public ResponseEntity<List<Post>> getFeedPast(@RequestParam Date date) {
	// System.out.println("Getting Feed after date:\n\t" + date);
	//
	// // List<Post> posts = postService.getAllPostsPast(date);
	//
	// return ResponseEntity.ok(posts);
	// }

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
