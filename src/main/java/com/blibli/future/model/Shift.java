package com.blibli.future.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class Shift {
	@Id
	private String id;

	private String name;
	private LocalTime startHour;
	private LocalTime endHour;
	private DayOfWeek startDay;
	private DayOfWeek endDay;
	private String departmentEmployee;
	private String location;

	public Shift() {}

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

	public LocalTime getStartHour() {
		return startHour;
	}

	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}

	public LocalTime getEndHour() {
		return endHour;
	}

	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}

	public DayOfWeek getStartDay() {
		return startDay;
	}

	public void setStartDay(DayOfWeek startDay) {
		this.startDay = startDay;
	}

	public DayOfWeek getEndDay() {
		return endDay;
	}

	public void setEndDay(DayOfWeek endDay) {
		this.endDay = endDay;
	}

	public String getDepartmentEmployee() {
		return departmentEmployee;
	}

	public void setDepartmentEmployee(String departmentEmployee) {
		this.departmentEmployee = departmentEmployee;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Shift shift = (Shift) o;

		if(id != null ? !id.equals(shift.id) : shift.id != null) return false;
		if(name != null ? !name.equals(shift.name) : shift.name != null) return false;
		if(startHour != null ? !startHour.equals(shift.startHour) : shift.startHour != null) return false;
		if(endHour != null ? !endHour.equals(shift.endHour) : shift.endHour != null) return false;
		if(startDay != shift.startDay) return false;
		if(endDay != shift.endDay) return false;
		if(departmentEmployee != null ? !departmentEmployee.equals(shift.departmentEmployee) : shift.departmentEmployee != null)
			return false;
		return location != null ? location.equals(shift.location) : shift.location == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (startHour != null ? startHour.hashCode() : 0);
		result = 31 * result + (endHour != null ? endHour.hashCode() : 0);
		result = 31 * result + (startDay != null ? startDay.hashCode() : 0);
		result = 31 * result + (endDay != null ? endDay.hashCode() : 0);
		result = 31 * result + (departmentEmployee != null ? departmentEmployee.hashCode() : 0);
		result = 31 * result + (location != null ? location.hashCode() : 0);
		return result;
	}
}
