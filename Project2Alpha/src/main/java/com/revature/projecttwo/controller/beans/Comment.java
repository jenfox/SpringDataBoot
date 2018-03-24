package com.revature.projecttwo.controller.beans;

public class Comment {

	private int id;
	private int authorId;
	private int receiverId;
	private int postId;
	private String content;

	public Comment() {
	}

	public Comment(int id, int authorId, int receiverId, int postId, String content) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.receiverId = receiverId;
		this.postId = postId;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
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
		return "Comment [id=" + id + ", authorId=" + authorId + ", receiverId=" + receiverId + ", postId=" + postId
				+ ", content=" + content + "]";
	}

}
