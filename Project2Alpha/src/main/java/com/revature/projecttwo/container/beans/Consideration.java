package com.revature.projecttwo.container.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Consideration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Resident author;

	@ManyToOne
	private Post post;

	@Size(max = 500)
	private String content;

	public Consideration() {
	}

	public Consideration(int id, Resident author, Post post, String content) {
		super();
		this.id = id;
		this.author = author;
		this.post = post;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Resident getAuthor() {
		return author;
	}

	public void setAuthor(Resident authorId) {
		this.author = authorId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", post=" + post + ", content=" + content + "]";
	}

}
