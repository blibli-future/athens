package com.blibli.future.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.blibli.future.enums.Gender;

@Entity
public class Leave {
	@Id
	private String id;

	private String name;
	private Gender gender;
	private String maritalStatus;
	private String religion;
	
	public Leave(String id, String name, Gender gender, String maritalStatus, String religion){
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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
