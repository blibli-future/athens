package com.blibli.future.vo;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ShiftVo {
	private String id;
    private String name;
    private LocalTime startHour;
    private LocalTime endHour;
    private DayOfWeek startDay;
    private DayOfWeek endDay;
    private String departmentEmployee;
    private String location;

    public ShiftVo() {
    }

    public ShiftVo(String id, String name, LocalTime startHour, LocalTime endHour, DayOfWeek startDay, DayOfWeek endDay, String departmentEmployee, String location) {
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ShiftVo shiftVO = (ShiftVo) o;

        if(name != null ? !name.equals(shiftVO.name) : shiftVO.name != null) return false;
        if(startHour != null ? !startHour.equals(shiftVO.startHour) : shiftVO.startHour != null) return false;
        if(endHour != null ? !endHour.equals(shiftVO.endHour) : shiftVO.endHour != null) return false;
        if(startDay != shiftVO.startDay) return false;
        if(endDay != shiftVO.endDay) return false;
        if(departmentEmployee != null ? !departmentEmployee.equals(shiftVO.departmentEmployee) : shiftVO.departmentEmployee != null)
            return false;
        return location != null ? location.equals(shiftVO.location) : shiftVO.location == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startHour != null ? startHour.hashCode() : 0);
        result = 31 * result + (endHour != null ? endHour.hashCode() : 0);
        result = 31 * result + (startDay != null ? startDay.hashCode() : 0);
        result = 31 * result + (endDay != null ? endDay.hashCode() : 0);
        result = 31 * result + (departmentEmployee != null ? departmentEmployee.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
