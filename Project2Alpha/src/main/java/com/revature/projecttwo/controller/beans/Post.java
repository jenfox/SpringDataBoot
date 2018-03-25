package com.revature.projecttwo.controller.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * It's a POST Bean!
 *
 */
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Integer[] likes;
	private String content;
	private String youtubeUrl;
	private String imageUrl;
	private int authorId;

	public Post() {

	}

	public Post(int id, Integer[] likes, String content, String youtubeUrl, String imageUrl, int authorId) {
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

	public Integer[] getLikes() {
		return likes;
	}

	public void setLikes(Integer[] likes) {
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

}
