package com.revature.projecttwo.container.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@Override
	public String toString() {
		return "Notification [id=" + id + ", user=" + user + ", content=" + content + ", url=" + url + "]";
	}

}
