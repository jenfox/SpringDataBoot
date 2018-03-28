package com.revature.projecttwo.container.validation;

import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Post;

@Service
public class PostValidService {

	public boolean checkPostContentAndAuthor(Post post) {
		return post != null && post.getAuthor() != null;

	}
}
