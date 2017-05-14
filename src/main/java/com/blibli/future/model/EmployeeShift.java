package com.blibli.future.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeeShift {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String nik;
	private String idShift;

	public EmployeeShift() {}

	public EmployeeShift(String idShift, String nik){
		this.idShift = idShift;
		this.nik = nik;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getIdShift() {
		return idShift;
	}

	public void setIdShift(String idShift) {
		this.idShift = idShift;
	}

}
