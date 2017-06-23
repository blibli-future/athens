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
	private DayOfWeek startDay;
	private DayOfWeek endDay;
	private String departmentEmployee;
	private String location;
	@ManyToMany(mappedBy = "shifts")
	private Set<Employee> employees;
	
	public Shift() {}
	
	public Shift(String id, String name, LocalTime startHour, LocalTime endHour, DayOfWeek startDay, DayOfWeek endDay, String departmentEmployee, String location) {
        this.id = id;
    	this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startDay = startDay;
        this.endDay = endDay;
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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployees(Employee employees) {
		this.employees.add(employees);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentEmployee == null) ? 0 : departmentEmployee.hashCode());
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((endDay == null) ? 0 : endDay.hashCode());
		result = prime * result + ((endHour == null) ? 0 : endHour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDay == null) ? 0 : startDay.hashCode());
		result = prime * result + ((startHour == null) ? 0 : startHour.hashCode());
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
		Shift other = (Shift) obj;
		if (departmentEmployee == null) {
			if (other.departmentEmployee != null)
				return false;
		} else if (!departmentEmployee.equals(other.departmentEmployee))
			return false;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (endDay != other.endDay)
			return false;
		if (endHour == null) {
			if (other.endHour != null)
				return false;
		} else if (!endHour.equals(other.endHour))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDay != other.startDay)
			return false;
		if (startHour == null) {
			if (other.startHour != null)
				return false;
		} else if (!startHour.equals(other.startHour))
			return false;
		return true;
	}

}
