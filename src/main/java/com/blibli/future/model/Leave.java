package com.blibli.future.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Leave {
	@Id
	private String id;

	private String name;
	private String gender;
	private String maritalStatus;
	private String religion;
	
	public Leave(String id, String name, String gender, String maritalStatus, String religion){
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.religion = religion;
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
}
