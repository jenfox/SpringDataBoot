package com.revature.projecttwo.container.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Resident user;

	@Size(max = 200)
	private String content;

	private String url;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreated", nullable = false)
	private Date dateCreated;

	// assign date on create
	@PrePersist
	protected void onCreate() {
		dateCreated = new Date();
	}

	public Notification() {

	}

	public Notification(int id, Resident user, String content, String url) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Resident getUser() {
		return user;
	}

	public void setUser(Resident user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", user=" + user + ", content=" + content + ", url=" + url + "]";
	}

}
