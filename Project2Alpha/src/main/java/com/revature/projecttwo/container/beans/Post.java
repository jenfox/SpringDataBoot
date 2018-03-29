package com.revature.projecttwo.container.beans;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

	@ManyToOne
	private Resident author;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreated", nullable = false)
	private Date dateCreated;

	@OneToMany
	private List<Consideration> considerations;

	// assign date on create
	@PrePersist
	protected void onCreate() {
		dateCreated = new Date();
	}

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

	public void setAuthor(Resident author) {
		this.author = author;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<Consideration> getConsiderations() {
		return considerations;
	}

	public void setConsiderations(List<Consideration> considerations) {
		this.considerations = considerations;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", likes=" + Arrays.toString(likes) + ", content=" + content + ", youtubeUrl="
				+ youtubeUrl + ", imageUrl=" + imageUrl + ", author=" + author + ", dateCreated=" + dateCreated + "]";
	}

}
