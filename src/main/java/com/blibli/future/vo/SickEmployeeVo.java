package com.blibli.future.vo;

public class SickEmployeeVo {
	private String nik;
	private String fullName;
	private String department;
	private Long numberOfSick;
	
	public SickEmployeeVo(String nik, String fullName, String department, Long numberOfSick){
		this.nik = nik;
		this.fullName = fullName;
		this.department = department;
		this.numberOfSick = numberOfSick;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getNumberOfSick() {
		return numberOfSick;
	}

	public void setNumberOfSick(Long numberOfSick) {
		this.numberOfSick = numberOfSick;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result + ((numberOfSick == null) ? 0 : numberOfSick.hashCode());
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
		SickEmployeeVo other = (SickEmployeeVo) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (numberOfSick == null) {
			if (other.numberOfSick != null)
				return false;
		} else if (!numberOfSick.equals(other.numberOfSick))
			return false;
		return true;
	}
	
	
}
