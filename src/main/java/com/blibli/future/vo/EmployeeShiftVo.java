package com.blibli.future.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeShiftVo implements Serializable{
	private static final long serialVersionUID = 4695772351882114738L;
	private String id;
	private String nik;
	private String idShift;
	
	public EmployeeShiftVo(String id, String idShift, String nik){
		this.id = id;
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