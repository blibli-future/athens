package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeShiftVo implements Serializable{
	private static final long serialVersionUID = 4695772351882114738L;
	private String nik;
	private String idShift;
	
	public EmployeeShiftVo(){}
	
	public EmployeeShiftVo(String idShift, String nik){
		this.idShift = idShift;
		this.nik = nik;
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

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		EmployeeShiftVo that = (EmployeeShiftVo) o;

		if(nik != null ? !nik.equals(that.nik) : that.nik != null) return false;
		return idShift != null ? idShift.equals(that.idShift) : that.idShift == null;
	}

	@Override
	public int hashCode() {
		int result = nik != null ? nik.hashCode() : 0;
		result = 31 * result + (idShift != null ? idShift.hashCode() : 0);
		return result;
	}
}