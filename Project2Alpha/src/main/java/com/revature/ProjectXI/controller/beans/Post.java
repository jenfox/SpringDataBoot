package com.revature.ProjectXI.controller.beans;

import java.util.List;

/**
 * 
 * It's a POST Bean!
 *
 */
public class Post {

	private int id;
	private List<Integer> likes;
	private String content;
	private String youtubeUrl;
	private String imageUrl;
	private int authorId;

	public Post() {

	}

	public Post(int id, List<Integer> likes, String content, String youtubeUrl, String imageUrl, int authorId) {
		super();
		this.id = id;
		this.likes = likes;
		this.content = content;
		this.youtubeUrl = youtubeUrl;
		this.imageUrl = imageUrl;
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getLikes() {
		return likes;
	}

	public void setLikes(List<Integer> likes) {
		this.likes = likes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", likes=" + likes + ", content=" + content + ", YoutubeUrl=" + youtubeUrl
				+ ", imageUrl=" + imageUrl + ", authorId=" + authorId + "]";
	}

}
