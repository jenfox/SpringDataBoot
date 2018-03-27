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
import org.springframework.web.multipart.MultipartFile;

import com.revature.projecttwo.bucket.AmazonClient;
import com.revature.projecttwo.container.beans.Comment;
import com.revature.projecttwo.container.beans.Notification;
import com.revature.projecttwo.container.beans.Post;
import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.dtos.UserDto;
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
	@Autowired
	private AmazonClient ac;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<Boolean> register(@RequestBody Resident user) {
		System.out.println("Registering User:\n\t " + user);
		String plainTextPassword = user.getPassword();

		if (userService.registerNewUserAccount(user)) {

			// Email Registered
			emailService.sendRegister(user.getEmail(), plainTextPassword);

			return ResponseEntity.ok(true);
		}
		// unable to register user
		return ResponseEntity.ok(false);
	}

	// email, password
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Resident> login(@RequestBody Resident user) {
		System.out.println("Logging in User:\n\t " + user);

		Resident userFound = userService.getUser(user.getEmail(), user.getPassword());
		if (userFound == null) {
			System.out.println("No user Found with email password combination");
			return ResponseEntity.ok(null);

		}
		return ResponseEntity.ok(userFound);
	}

	// email
	@RequestMapping(method = RequestMethod.POST, value = "/reset")
	public ResponseEntity<Boolean> resetPassword(@RequestBody Resident userSkeleton) {
		System.out.println("Reset Password:\n\t " + userSkeleton.getEmail());

		// validate email
		Resident user = userService.getUser(userSkeleton.getEmail());

		userService.resetPassword(user);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	public ResponseEntity<Resident> getProfile(@PathVariable Integer id) {
		System.out.println("Getting user profile:\n\t " + id);

		Resident user = userService.getUser(id);

		return ResponseEntity.ok(user);
	}

	// first, last
	@RequestMapping(method = RequestMethod.GET, value = "/users/{firstName}/{lastName}")
	public ResponseEntity<List<UserDto>> getUsers(@PathVariable String firstName, @PathVariable String lastName) {

		System.out.println("Getting user:\n\t " + firstName + " " + lastName);

		List<UserDto> users = userService.getUsers(firstName, lastName);

		return ResponseEntity.ok(users);
	}

	// name
	@RequestMapping(method = RequestMethod.GET, value = "/users/find/{name}")
	public ResponseEntity<List<UserDto>> getUserByMatch(@PathVariable String name) {

		System.out.println("Finding users matching:\n\t " + name);

		List<UserDto> users = userService.getUsersDtosMatching(name);

		return ResponseEntity.ok(users);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/{id}")
	public ResponseEntity<Resident> updateProfile(@PathVariable Integer id, @RequestBody Resident user) {
		System.out.println("Updating User:\n\t " + user);

		return ResponseEntity.ok(userService.updateUser(user, id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/password")
	public ResponseEntity<Boolean> changePassword(@RequestBody Resident userSkeleton) {
		System.out.println("Resetting Password to:\n\t " + userSkeleton);

		return ResponseEntity.ok(userService.changePassword(userSkeleton));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/profilePictures/{uid}")
	public ResponseEntity<Boolean> updateProfilePic(@RequestBody MultipartFile multipartFile, @PathVariable int uid) {
		System.out.println("Updating Profile Pic:\n\t ");

		// String filepath = "C:\\Users\\Joshua\\Pictures\\Memes\\testPic.png";

		String url = ac.uploadFile(multipartFile);
		userService.updateUserImage(url, uid);
		System.out.println(url);

		return ResponseEntity.ok(true);

	}

	// content, imageUrl, youtubeUrl
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

	// int
	@RequestMapping(method = RequestMethod.POST, value = "/likes/{postId}")
	public ResponseEntity<Boolean> likePost(@PathVariable Integer postId) {
		System.out.println("Like Post:\n\t " + postId);

		// TODO get uid
		int userId = 0;
		postService.likePost(postId, userId);

		return ResponseEntity.ok(true);
	}

	// content, postId
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
