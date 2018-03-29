package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Consideration;
import com.revature.projecttwo.container.repo.CommentRepo;

@Service
public class CommentService {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private CommentRepo commentRepo;

	public List<Consideration> getAllComments() {
		logger.info("Getting all comments:\n\t");
		List<Consideration> comments = new ArrayList<>();
		// method reference add method call
		commentRepo.findAll().forEach(comments::add);

		return comments;
	}

	public Consideration getComment(Integer id) {
		logger.info("Found Comment in DB:\n\t" + id);
		Optional<Consideration> comment = commentRepo.findById(id);

		return comment.get();
	}

	public void addComment(Consideration comment) {
		logger.info("Saving Comment to DB:\n\t" + comment);
		commentRepo.save(comment);
	}

	public void deleteComment(Consideration comment, String id) {
		logger.info("Updating Comment to DB:\n\t" + comment);
		commentRepo.save(comment);
	}

	public void deleteComment(Integer id) {
		logger.info("Deleting Comment to DB:+\n\t" + id);
		commentRepo.deleteById(id);
	}
}
