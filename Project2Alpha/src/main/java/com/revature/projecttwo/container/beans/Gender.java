package com.revature.projecttwo.container.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gender {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String label;

	public String getLabel() {
		return label;
	}

	public Gender() {
	}

	public Gender(String label) {
		super();
		this.label = label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Gender [label=" + label + "]";
	}

}
