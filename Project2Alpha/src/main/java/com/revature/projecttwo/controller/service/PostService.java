package com.revature.projecttwo.controller.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.controller.beans.Post;
import com.revature.projecttwo.controller.repo.PostRepo;

@Service
public class PostService {

	@Autowired
	private PostRepo postRepo;

	public List<Post> getAllPosts() {
		System.out.println("Getting all posts:\n\t");
		List<Post> posts = new ArrayList<>();
		// method reference add method call
		postRepo.findAll().forEach(posts::add);

		return posts;
	}

	public Post getPost(Integer id) {
		System.out.println("Found Post in DB:\n\t" + id);
		Optional<Post> post = postRepo.findById(id);

		return post.get();
	}

	public void addPost(Post post) {
		System.out.println("Saving Post to DB:\n\t" + post);
		postRepo.save(post);
	}

	public void updatePost(Post post, String id) {
		System.out.println("Updating Post to DB:\n\t" + post);
		postRepo.save(post);
	}

	public void deletePost(Integer id) {
		System.out.println("Deleting Post to DB:+\n\t" + id);
		postRepo.deleteById(id);
	}

	public void likePost(Integer postId, Integer userId) {
		System.out.println("Likeing Post to DB:+\n\t" + postId + " from user " + userId);
		Post post = postRepo.findById(postId).get();

		List<Integer> likes = new ArrayList<>(Arrays.asList(post.getLikes()));

		likes.add(userId);

		post.setLikes(likes.toArray(new Integer[likes.size()]));

		postRepo.save(post);

	}
}
