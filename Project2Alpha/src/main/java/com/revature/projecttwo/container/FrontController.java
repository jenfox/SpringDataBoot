package com.revature.projecttwo.container;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.revature.projecttwo.container.beans.Consideration;
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
	private static Logger logger = LogManager.getLogger();

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
		logger.info("Registering User:\n\t " + user);
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
		logger.info("Logging in User:\n\t " + user);

		Resident userFound = userService.getUser(user.getEmail(), user.getPassword());
		if (userFound == null) {
			logger.warn("No user Found with email password combination");
			return ResponseEntity.ok(null);

		}
		return ResponseEntity.ok(userFound);
	}

	// email
	@RequestMapping(method = RequestMethod.POST, value = "/reset")
	public ResponseEntity<Boolean> resetPassword(@RequestBody Resident userSkeleton) {
		logger.info("Reset Password:\n\t " + userSkeleton.getEmail());

		// validate email
		Resident user = userService.getUser(userSkeleton.getEmail());

		userService.resetPassword(user);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	public ResponseEntity<Resident> getProfile(@PathVariable Integer id) {
		logger.info("Getting user profile:\n\t " + id);

		Resident user = userService.getUser(id);

		return ResponseEntity.ok(user);
	}

	// first, last
	@RequestMapping(method = RequestMethod.GET, value = "/users/{firstName}/{lastName}")
	public ResponseEntity<List<UserDto>> getUsers(@PathVariable String firstName, @PathVariable String lastName) {

		logger.info("Getting user:\n\t " + firstName + " " + lastName);

		List<UserDto> users = userService.getUsers(firstName, lastName);

		return ResponseEntity.ok(users);
	}

	// name
	@RequestMapping(method = RequestMethod.GET, value = "/users/find/{name}")
	public ResponseEntity<List<UserDto>> getUserByMatch(@PathVariable String name) {

		logger.info("Finding users matching:\n\t " + name);

		List<UserDto> users = userService.getUsersDtosMatching(name);

		return ResponseEntity.ok(users);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/{id}")
	public ResponseEntity<Resident> updateProfile(@PathVariable Integer id, @RequestBody Resident user) {
		logger.info("Updating User:\n\t " + user);

		return ResponseEntity.ok(userService.updateUser(user, id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/password")
	public ResponseEntity<Boolean> changePassword(@RequestBody Resident userSkeleton) {
		logger.info("Resetting Password to:\n\t " + userSkeleton);

		return ResponseEntity.ok(userService.changePassword(userSkeleton));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "/profilePictures/{uid}")
	public ResponseEntity<Resident> updateProfilePic(@RequestBody MultipartFile multipartFile, @PathVariable int uid) {
		logger.info("Updating Profile Pic:\n\t " + multipartFile.getName());

		// saves the url we append to the bucket endpoint so we can save it in the
		// database

		String url = ac.uploadFile(multipartFile);
		userService.updateUserImage(url, uid);
		logger.info(url);

		Resident userFound = userService.getUser(uid);

		return ResponseEntity.ok(userFound);

	}

	// content, imageUrl, youtubeUrl
	@RequestMapping(method = RequestMethod.POST, value = "/posts")
	public ResponseEntity<Boolean> savePost(@RequestBody Post post) {
		logger.info("Saving Post:\n\t " + post);

		// TODO validate

		postService.addPost(post);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/posts")
	public ResponseEntity<List<Post>> getFeed() {
		logger.info("Getting All Posts:\n\t ");

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
		logger.info("Getting Feed:\n\t ");

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
	// logger.info("Getting Feed after date:\n\t" + date);
	//
	// List<Post> posts = postService.getAllPostsPast(date);
	//
	// return ResponseEntity.ok(posts);
	// }

	// int
	@RequestMapping(method = RequestMethod.POST, value = "/likes/{postId}")
	public ResponseEntity<Post> likePost(@PathVariable Integer postId, @RequestBody Resident userSkeleton) {
		logger.info("Like Post:\n\t " + postId);

		Post post = postService.likePost(postId, userSkeleton.getId());

		return ResponseEntity.ok(post);
	}

	// content, postId
	@RequestMapping(method = RequestMethod.POST, value = "/comments")
	public ResponseEntity<Boolean> comment(@RequestBody Consideration comment) {
		logger.info("Commenting:\n\t " + comment);

		commentService.addComment(comment);

		return ResponseEntity.ok(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/comments")
	public ResponseEntity<List<Consideration>> getComments() {
		logger.info("Getting comments:\n\t ");

		List<Consideration> comments = commentService.getAllComments();

		return ResponseEntity.ok(comments);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/notifications")
	public ResponseEntity<List<Notification>> getNotifications() {
		logger.info("Getting Notifications:\n\t ");

		List<Notification> notifications = notificationService.getAllNotification();

		return ResponseEntity.ok(notifications);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/notifications")
	public ResponseEntity<Boolean> addNotification(@RequestBody Notification notification) {
		logger.info("Adding notification:\n\t " + notification);

		notificationService.addNotification(notification);

		return ResponseEntity.ok(true);
	}

}
