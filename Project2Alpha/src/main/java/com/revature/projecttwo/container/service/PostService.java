package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Post;
import com.revature.projecttwo.container.repo.PostRepo;
import com.revature.projecttwo.container.validation.PostValidService;

@Service
public class PostService {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private PostValidService postValidator;
	@Autowired
	private UserService userService;

	/**
	 * Gets all the posts!
	 * 
	 * @return
	 */
	public List<Post> getAllPosts() {
		logger.info("Getting all posts:\n\t");
		List<Post> posts = new ArrayList<>();
		// method reference add method call
		postRepo.findAll().forEach(posts::add);

		// Remove User password field from post user obj
		for (Post p : posts) {
			if (p.getAuthor() != null)
				p.getAuthor().setPassword("");
		}

		return posts;
	}

	/**
	 * Gets the newest 20 posts in DB
	 * 
	 * @return
	 */
	public List<Post> getPrevious20Posts() {
		logger.info("Getting last 20 posts:\n\t");
		List<Post> posts = new ArrayList<>();
		// method reference add method call
		postRepo.findTop21ByOrderByDateCreatedDesc().forEach(posts::add);

		// Remove User password field from post user obj
		for (Post p : posts) {
			if (p.getAuthor() != null)
				p.getAuthor().setPassword("");
		}

		return posts;
	}
	//
	// public List<Post> getAllPostsPast(Date date) {
	// logger.info("Getting all posts past date:\n\t" + date);
	// List<Post> posts = new ArrayList<>();
	// // method reference add method call
	// postRepo.findTop20ByGreaterThanDateCreatedOrderByDateCreated(date).forEach(posts::add);
	//
	// return posts;
	// }

	/**
	 * Gets post by id
	 * 
	 * @param id
	 * @return
	 */
	public Post getPost(Integer id) {
		logger.info("Found Post in DB:\n\t" + id);
		Optional<Post> post = postRepo.findById(id);
		Post postFound = post.get();

		// Remove User password field
		if (postFound != null && postFound.getAuthor() != null)
			postFound.getAuthor().setPassword("");

		return post.get();
	}

	public List<Post> getPostByAuthorId(Integer authorId) {
		// 1. check if author exists
		if (userService.getUser(authorId) == null) {
			logger.error("Author ID set in post DNE:\n\t");
			return null;
		}

		// 2. Get the posts
		logger.info("Getting last 20 posts:\n\t");
		List<Post> posts = new ArrayList<>();
		// method reference add method call
		postRepo.findByAuthorId(authorId).forEach(posts::add);

		// 3. Remove User password field from post user obj
		for (Post p : posts) {
			if (p.getAuthor() != null)
				p.getAuthor().setPassword("");
		}

		return posts;
	}

	public boolean addPost(Post post) {
		// 1. Check if post has valid fields
		if (!postValidator.checkPostContentAndAuthor(post)) {
			logger.error("Unable to save post to DB:\n\t" + post);
			return false;
		}

		logger.info("Saving Post to DB:\n\t" + post);

		return postRepo.save(post) != null;
	}

	public boolean updatePost(Post post, String id) {
		// 1. Check if post has valid fields
		if (!postValidator.checkPostContentAndAuthor(post)) {
			logger.error("Unable to save post to DB:\n\t" + post);
			return false;
		}

		logger.info("Updating Post to DB:\n\t" + post);
		return postRepo.save(post) != null;
	}

	public Post likePost(Integer postId, Integer userId) {

		// 1. check if userId exists
		if (userService.getUser(userId) == null) {
			logger.error("User ID liking post DNE:\n\t");
			return null;
		}

		// 2. Find if post exists
		Post post = postRepo.findById(postId).get();

		if (post == null) {
			logger.error("No valid post to like\n\t" + postId);
			return null;
		}
		if (post.getLikes() == null)
			post.setLikes(new Integer[] {});

		logger.info("Likeing Post to DB:+\n\t" + postId + " from user\n\t" + userId);

		// change Like array to include new UID
		List<Integer> likes = new ArrayList<>(Arrays.asList(post.getLikes()));

		likes.add(userId);

		post.setLikes(likes.toArray(new Integer[likes.size()]));

		return postRepo.save(post);

	}

	public void deletePost(Integer id) {
		logger.info("Deleting Post to DB:+\n\t" + id);
		postRepo.deleteById(id);
	}

}
