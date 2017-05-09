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
}
