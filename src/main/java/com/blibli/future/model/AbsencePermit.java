package com.blibli.future.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class AbsencePermit {
	@Id
	private String id;
	private String name;
	@OneToMany(mappedBy = "absencePermit")
    private Set<EmployeeAbsencePermit> employeeAbsencesPermit;

	public AbsencePermit() {}

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

}
