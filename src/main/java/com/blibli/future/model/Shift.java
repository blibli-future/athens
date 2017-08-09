package com.blibli.future.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Shift {
	@Id
	private String id;

	private String name;
	private LocalTime startHour;
	private LocalTime endHour;
	private DayOfWeek workDay;
	private String departmentEmployee;
	private String location;
	@ManyToMany(mappedBy = "shifts")
	private Set<Employee> employees;
	
	public Shift() {}

	public Shift(String id, String name, LocalTime startHour, LocalTime endHour, DayOfWeek workDay, String departmentEmployee, String location) {
		this.id = id;
		this.name = name;
		this.startHour = startHour;
		this.endHour = endHour;
		this.workDay = workDay;
		this.departmentEmployee = departmentEmployee;
		this.location = location;
	}

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

	public DayOfWeek getWorkDay() {
		return workDay;
	}

	public void setWorkDay(DayOfWeek workDay) {
		this.workDay = workDay;
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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
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
		if(workDay != shift.workDay) return false;
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
		result = 31 * result + (workDay != null ? workDay.hashCode() : 0);
		result = 31 * result + (departmentEmployee != null ? departmentEmployee.hashCode() : 0);
		result = 31 * result + (location != null ? location.hashCode() : 0);
		return result;
	}
}
