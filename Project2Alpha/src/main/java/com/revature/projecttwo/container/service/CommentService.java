package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Comment;
import com.revature.projecttwo.container.repo.CommentRepo;

@Service
public class CommentService {

	@Autowired
	private CommentRepo commentRepo;

	public List<Comment> getAllComments() {
		System.out.println("Getting all comments:\n\t");
		List<Comment> comments = new ArrayList<>();
		// method reference add method call
		commentRepo.findAll().forEach(comments::add);

		return comments;
	}

	public Comment getComment(Integer id) {
		System.out.println("Found Comment in DB:\n\t" + id);
		Optional<Comment> comment = commentRepo.findById(id);

		return comment.get();
	}

	public void addComment(Comment comment) {
		System.out.println("Saving Comment to DB:\n\t" + comment);
		commentRepo.save(comment);
	}

	public void deleteComment(Comment comment, String id) {
		System.out.println("Updating Comment to DB:\n\t" + comment);
		commentRepo.save(comment);
	}

	public void deleteComment(Integer id) {
		System.out.println("Deleting Comment to DB:+\n\t" + id);
		commentRepo.deleteById(id);
	}
}
