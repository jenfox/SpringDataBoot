package com.revature.projecttwo.controller.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * User Bean ~ User Table
 * 
 * RENAMED to user, the SQL table "user" table is a reserved table
 *
 */
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private int genderId;
	private int sexualOrientId;
	@Column(unique = true)
	private String phoneNumber;
	private Date dob;
	@Column(unique = true)
	private String profileUrl;

	public Users() {

	}

	public Users(Integer id, String email, String firstName, String lastName, String password, int genderId,
			int sexualOrientId, String phoneNumber, Date dob, String profileUrl) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.genderId = genderId;
		this.sexualOrientId = sexualOrientId;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.profileUrl = profileUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
