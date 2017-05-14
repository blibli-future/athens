package com.blibli.future.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;

@Entity
public class Leave {
	@Id
	private String id;

	private String name;
	private Gender gender;
	private MaritalStatus maritalStatus;
	private Religion religion;
	
	public Leave(String id, String name, Gender gender, MaritalStatus maritalStatus, Religion religion){
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.religion = religion;
		this.id = id;
		this.name = name;
	}

	public Leave() {}

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

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}
}
