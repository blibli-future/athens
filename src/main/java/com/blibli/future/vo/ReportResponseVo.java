package com.blibli.future.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponseVo implements Serializable{
	private static final long serialVersionUID = 2006346112358122493L;
	private String nik;
	private String fullName;
	private String department;
	private double daysComing;
	private double daysAbsence;
	private double sick;
	private double unpaidLeave;
	private double yearlyLeave;
	private double leaveEarly;
	private double lateWithoutPermission;
	private double lateWithPermission;
	private double hourlyLeave;
	private double replacementLeave;
	private double noTapOutDay;
	
	public ReportResponseVo(){}
	
	public ReportResponseVo(String nik, String fullName, String department, double daysComing, double daysAbsence, double sick,
			double unpaidLeave, double yearlyLeave, double leaveEarly, double lateWithoutPermission,
			double lateWithPermission, double hourlyLeave, double replacementLeave, double noTapOutDay) {
		this.nik = nik;
		this.fullName = fullName;
		this.department = department;
		this.daysComing = daysComing;
		this.daysAbsence = daysAbsence;
		this.sick = sick;
		this.unpaidLeave = unpaidLeave;
		this.yearlyLeave = yearlyLeave;
		this.leaveEarly = leaveEarly;
		this.lateWithoutPermission = lateWithoutPermission;
		this.lateWithPermission = lateWithPermission;
		this.hourlyLeave = hourlyLeave;
		this.replacementLeave = replacementLeave;
		this.noTapOutDay = noTapOutDay;
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

	public double getDaysComing() {
		return daysComing;
	}

	public void setDaysComing(double daysComing) {
		this.daysComing = daysComing;
	}

	public double getDaysAbsence() {
		return daysAbsence;
	}

	public void setDaysAbsence(double daysAbsence) {
		this.daysAbsence = daysAbsence;
	}

	public double getSick() {
		return sick;
	}

	public void setSick(double sick) {
		this.sick = sick;
	}

	public double getUnpaidLeave() {
		return unpaidLeave;
	}

	public void setUnpaidLeave(double unpaidLeave) {
		this.unpaidLeave = unpaidLeave;
	}

	public double getYearlyLeave() {
		return yearlyLeave;
	}

	public void setYearlyLeave(double yearlyLeave) {
		this.yearlyLeave = yearlyLeave;
	}

	public double getLeaveEarly() {
		return leaveEarly;
	}

	public void setLeaveEarly(double leaveEarly) {
		this.leaveEarly = leaveEarly;
	}

	public double getLateWithoutPermission() {
		return lateWithoutPermission;
	}

	public void setLateWithoutPermission(double lateWithoutPermission) {
		this.lateWithoutPermission = lateWithoutPermission;
	}

	public double getLateWithPermission() {
		return lateWithPermission;
	}

	public void setLateWithPermission(double lateWithPermission) {
		this.lateWithPermission = lateWithPermission;
	}

	public double getHourlyLeave() {
		return hourlyLeave;
	}

	public void setHourlyLeave(double hourlyLeave) {
		this.hourlyLeave = hourlyLeave;
	}

	public double getReplacementLeave() {
		return replacementLeave;
	}

	public void setReplacementLeave(double replacementLeave) {
		this.replacementLeave = replacementLeave;
	}

	public double getNoTapOutDay() {
		return noTapOutDay;
	}

	public void setNoTapOutDay(double noTapOutDay) {
		this.noTapOutDay = noTapOutDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(daysAbsence);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(daysComing);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		temp = Double.doubleToLongBits(hourlyLeave);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lateWithPermission);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lateWithoutPermission);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(leaveEarly);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		temp = Double.doubleToLongBits(noTapOutDay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementLeave);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sick);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(unpaidLeave);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yearlyLeave);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ReportResponseVo other = (ReportResponseVo) obj;
		if (Double.doubleToLongBits(daysAbsence) != Double.doubleToLongBits(other.daysAbsence))
			return false;
		if (Double.doubleToLongBits(daysComing) != Double.doubleToLongBits(other.daysComing))
			return false;
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
		if (Double.doubleToLongBits(hourlyLeave) != Double.doubleToLongBits(other.hourlyLeave))
			return false;
		if (Double.doubleToLongBits(lateWithPermission) != Double.doubleToLongBits(other.lateWithPermission))
			return false;
		if (Double.doubleToLongBits(lateWithoutPermission) != Double.doubleToLongBits(other.lateWithoutPermission))
			return false;
		if (Double.doubleToLongBits(leaveEarly) != Double.doubleToLongBits(other.leaveEarly))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (Double.doubleToLongBits(noTapOutDay) != Double.doubleToLongBits(other.noTapOutDay))
			return false;
		if (Double.doubleToLongBits(replacementLeave) != Double.doubleToLongBits(other.replacementLeave))
			return false;
		if (Double.doubleToLongBits(sick) != Double.doubleToLongBits(other.sick))
			return false;
		if (Double.doubleToLongBits(unpaidLeave) != Double.doubleToLongBits(other.unpaidLeave))
			return false;
		if (Double.doubleToLongBits(yearlyLeave) != Double.doubleToLongBits(other.yearlyLeave))
			return false;
		return true;
	}
}
