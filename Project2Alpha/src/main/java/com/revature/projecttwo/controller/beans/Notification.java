package com.revature.projecttwo.controller.beans;

public class Notification {

	private int id;
	private int userId;
	private String content;
	private String url;

	public Notification() {

	}

	public Notification(int id, int userId, String content, String url) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		return "Notification [id=" + id + ", userId=" + userId + ", content=" + content + ", url=" + url + "]";
	}

}
