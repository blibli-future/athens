package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeRequestVo implements Serializable{
	private static final long serialVersionUID = -5803908690580045466L;
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
    private boolean status;

    public EmployeeRequestVo(){}
    
    public EmployeeRequestVo(String nik, String fullName, String gender, String position, String level, 
    		String organizationalUnitText, String maritalStatus, String religion, String nameOfDept, 
    		String chiefNik, String startWorkingDate) {
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
        this.status = true;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chiefNik == null) ? 0 : chiefNik.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((nameOfDept == null) ? 0 : nameOfDept.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result + ((organizationalUnitText == null) ? 0 : organizationalUnitText.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((religion == null) ? 0 : religion.hashCode());
		result = prime * result + ((startWorkingDate == null) ? 0 : startWorkingDate.hashCode());
		result = prime * result + (status ? 1231 : 1237);
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
		EmployeeRequestVo other = (EmployeeRequestVo) obj;
		if (chiefNik == null) {
			if (other.chiefNik != null)
				return false;
		} else if (!chiefNik.equals(other.chiefNik))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (maritalStatus == null) {
			if (other.maritalStatus != null)
				return false;
		} else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (nameOfDept == null) {
			if (other.nameOfDept != null)
				return false;
		} else if (!nameOfDept.equals(other.nameOfDept))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (organizationalUnitText == null) {
			if (other.organizationalUnitText != null)
				return false;
		} else if (!organizationalUnitText.equals(other.organizationalUnitText))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (religion == null) {
			if (other.religion != null)
				return false;
		} else if (!religion.equals(other.religion))
			return false;
		if (startWorkingDate == null) {
			if (other.startWorkingDate != null)
				return false;
		} else if (!startWorkingDate.equals(other.startWorkingDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
