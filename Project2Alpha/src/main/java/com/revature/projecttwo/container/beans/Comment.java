package com.revature.projecttwo.container.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Resident author;

	@ManyToOne
	private Resident recevier;

	@NotBlank
	private int postId;

	@Size(max = 500)
	private String content;

	public Comment() {
	}

	public Comment(int id, Resident author, Resident receiever, int postId, String content) {
		super();
		this.id = id;
		this.author = author;
		this.recevier = receiever;
		this.postId = postId;
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

	public Resident getReceiverId() {
		return recevier;
	}

	public void setReceiver(Resident receiverId) {
		this.recevier = receiverId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", receiver=" + recevier + ", postId=" + postId
				+ ", content=" + content + "]";
	}

}
