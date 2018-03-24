package com.revature.projecttwo.controller.beans;

import java.util.Date;

/**
 * 
 * User Bean ~ User Table
 *
 */
public class User {

	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private int genderId;
	private int sexualOrientId;
	private String phoneNumber;
	private Date dob;
	private String profileUrl;

	public User() {

	}

	public User(int id, String email, String firstName, String lastName, int genderId, int sexualOrientId,
			String phoneNumber, Date dob, String profileUrl) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.genderId = genderId;
		this.sexualOrientId = sexualOrientId;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.profileUrl = profileUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public int getSexualOrientId() {
		return sexualOrientId;
	}

	public void setSexualOrientId(int sexualOrientId) {
		this.sexualOrientId = sexualOrientId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", genderId=" + genderId + ", sexualOrientId=" + sexualOrientId + ", phoneNumber=" + phoneNumber
				+ ", dob=" + dob + ", profileUrl=" + profileUrl + "]";
	}

}
