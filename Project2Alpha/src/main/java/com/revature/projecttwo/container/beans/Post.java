package com.revature.projecttwo.container.beans;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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

	@NotNull
	private String content;

	private String youtubeUrl;

	private String imageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	private Resident author;

	private Date dateCreated;

	public Post() {

	}

	public Post(int id, Integer[] likes, String content, String youtubeUrl, String imageUrl, Resident author) {
		super();
		this.id = id;
		this.likes = likes;
		this.content = content;
		this.youtubeUrl = youtubeUrl;
		this.imageUrl = imageUrl;
		this.author = author;
		// on creation set date
		this.dateCreated = new java.sql.Date(Calendar.getInstance().getTime().getTime());
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

	public Resident getAuthor() {
		return author;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setAuthor(Resident author) {
		this.author = author;
	}

}
