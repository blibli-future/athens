package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeEditRequestVo implements Serializable{
	private static final long serialVersionUID = -8080680158663018022L;
	private String nik;
    private String fullName;
    private String gender;
    private String position;
    private String level;
    private String organizationalUnitText;
    private String maritalStatus;
    private String religion;
    private String nameOfDept;
    private String chiefNik;
    private String startWorkingDate;
    private String endWorkingDate;
    private boolean status;

    public EmployeeEditRequestVo(){}
    
    public EmployeeEditRequestVo(String nik, String fullName, String gender, String position, String level, 
    		String organizationalUnitText, String maritalStatus, String religion, String nameOfDept, 
    		String chiefNik, String startWorkingDate, String endWorkingDate, boolean status) {
        this.nik = nik;
        this.fullName = fullName;
        this.gender = gender;
        this.position = position;
        this.level = level;
        this.organizationalUnitText = organizationalUnitText;
        this.maritalStatus = maritalStatus;
        this.religion = religion;
        this.nameOfDept = nameOfDept;
        this.chiefNik = chiefNik;
        this.startWorkingDate = startWorkingDate;
        this.status = status;
        if(!status)
        {
        	this.endWorkingDate = endWorkingDate;
        }
        else
        	this.endWorkingDate = null;
    }

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOrganizationalUnitText() {
		return organizationalUnitText;
	}

	public void setOrganizationalUnitText(String organizationalUnitText) {
		this.organizationalUnitText = organizationalUnitText;
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

	public String getNameOfDept() {
		return nameOfDept;
	}

	public void setNameOfDept(String nameOfDept) {
		this.nameOfDept = nameOfDept;
	}

	public String getChiefNik() {
		return chiefNik;
	}

	public void setChiefNik(String chiefNik) {
		this.chiefNik = chiefNik;
	}

	public String getStartWorkingDate() {
		return startWorkingDate;
	}

	public void setStartWorkingDate(String startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	public String getEndWorkingDate() {
		return endWorkingDate;
	}

	public void setEndWorkingDate(String endWorkingDate) {
		this.endWorkingDate = endWorkingDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
