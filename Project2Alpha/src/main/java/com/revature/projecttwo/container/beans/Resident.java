package com.revature.projecttwo.container.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * User Bean ~ User Table
 * 
 * RENAMED to user, the SQL table "user" table is a reserved table
 *
 */
@Entity
public class Resident {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true)
	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String gender;

	@Column(unique = true)
	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(unique = true)
	private String profilePicUrl;

	public Resident() {

	}

	public Resident(Integer id, String email, String firstName, String lastName, String password, String genderId,
			String phoneNumber, Date dob, String profileUrl) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.gender = genderId;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.profilePicUrl = profileUrl;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
		return profilePicUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profilePicUrl = profileUrl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", genderId=" + gender + ", phoneNumber=" + phoneNumber + ", dob=" + dob + ", profileUrl="
				+ profilePicUrl + "]";
	}

}
