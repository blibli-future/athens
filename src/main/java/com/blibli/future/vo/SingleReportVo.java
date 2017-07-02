package com.blibli.future.vo;

public class SingleReportVo {
	private String nik;
	private String fullName;
	private String department;
	private Long numberResult;
	
	public SingleReportVo(){}
	
	public SingleReportVo(Object[] object){
		this.nik = object[0].toString();
		this.fullName = object[1].toString();
		this.department = object[2].toString();
		this.numberResult = Long.parseLong(object[3].toString());
	}
	
	public SingleReportVo(String nik, String fullName, String department, Long numberResult){
		this.nik = nik;
		this.fullName = fullName;
		this.department = department;
		this.numberResult = numberResult;
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

	public Long getNumberResult() {
		return numberResult;
	}

	public void setNumberResult(Long numberResult) {
		this.numberResult = numberResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result + ((numberResult == null) ? 0 : numberResult.hashCode());
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
		SingleReportVo other = (SingleReportVo) obj;
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
		if (numberResult == null) {
			if (other.numberResult != null)
				return false;
		} else if (!numberResult.equals(other.numberResult))
			return false;
		return true;
	}

	
}
