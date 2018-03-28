package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Post;
import com.revature.projecttwo.container.repo.PostRepo;
import com.revature.projecttwo.container.validation.PostValidService;

@Service
public class PostService {

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
		System.out.println("Getting all posts:\n\t");
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
		System.out.println("Getting last 20 posts:\n\t");
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
	// System.out.println("Getting all posts past date:\n\t" + date);
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
		System.out.println("Found Post in DB:\n\t" + id);
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
			System.out.println("Author ID set in post DNE:\n\t");
			return null;
		}

		// 2. Get the posts
		System.out.println("Getting last 20 posts:\n\t");
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
			System.out.println("Unable to save post to DB:\n\t" + post);
			return false;
		}

		System.out.println("Saving Post to DB:\n\t" + post);

		return postRepo.save(post) != null;
	}

	public boolean updatePost(Post post, String id) {
		// 1. Check if post has valid fields
		if (!postValidator.checkPostContentAndAuthor(post)) {
			System.out.println("Unable to save post to DB:\n\t" + post);
			return false;
		}

		System.out.println("Updating Post to DB:\n\t" + post);
		return postRepo.save(post) != null;
	}

	public boolean likePost(Integer postId, Integer userId) {

		// 1. check if userId exists
		if (userService.getUser(userId) == null) {
			System.out.println("User ID liking post DNE:\n\t");
			return false;
		}

		// 2. Find if post exists
		Post post = postRepo.findById(postId).get();

		if (post == null) {
			System.out.println("No valid post to like\n\t" + postId);
			return false;
		}
		if (post.getLikes() == null)
			post.setLikes(new Integer[] {});

		System.out.println("Likeing Post to DB:+\n\t" + postId + " from user\n\t" + userId);

		// change Like array to include new UID
		List<Integer> likes = new ArrayList<>(Arrays.asList(post.getLikes()));

		likes.add(userId);

		post.setLikes(likes.toArray(new Integer[likes.size()]));

		return postRepo.save(post) != null;

	}

	public void deletePost(Integer id) {
		System.out.println("Deleting Post to DB:+\n\t" + id);
		postRepo.deleteById(id);
	}

}
