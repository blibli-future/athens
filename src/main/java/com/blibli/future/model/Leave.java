package com.blibli.future.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy = "leave")
    private Set<EmployeeLeave> employeeLeave;
	
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

	public Set<EmployeeLeave> getEmployeeLeave() {
		return employeeLeave;
	}

	public void setEmployeeLeave(Set<EmployeeLeave> employeeLeave) {
		this.employeeLeave = employeeLeave;
	}
	
	public void addEmployeeLeave(EmployeeLeave employeeLeave) {
		this.employeeLeave.add(employeeLeave);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeLeave == null) ? 0 : employeeLeave.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((religion == null) ? 0 : religion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leave other = (Leave) obj;
		if (employeeLeave == null) {
			if (other.employeeLeave != null)
				return false;
		} else if (!employeeLeave.equals(other.employeeLeave))
			return false;
		if (gender != other.gender)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maritalStatus != other.maritalStatus)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (religion != other.religion)
			return false;
		return true;
	}
	
	
}
