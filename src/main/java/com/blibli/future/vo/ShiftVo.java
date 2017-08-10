package com.blibli.future.vo;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.blibli.future.model.Shift;

public class ShiftVo {
	private String id;
    private String name;
    private String startHour;
    private String endHour;
    private int workDay;
    private String departmentEmployee;
    private String location;
    private boolean assigned;

    public ShiftVo() {
    }

    public ShiftVo(Shift shift) {
        this.id = shift.getId();
        this.name = shift.getName();
        this.startHour = shift.getStartHour().toString();
        this.endHour = shift.getEndHour().toString();
        this.workDay = shift.getWorkDay().getValue();
        this.departmentEmployee = shift.getDepartmentEmployee();
        this.location = shift.getLocation();
        this.assigned = true;
    }

    public ShiftVo(String id, String name, LocalTime startHour, LocalTime endHour, DayOfWeek workDay, String departmentEmployee, String location, boolean assigned) {
        this.id = id;
        this.name = name;
        this.startHour = startHour.toString();
        this.endHour = endHour.toString();
        this.workDay = workDay.getValue();
        this.departmentEmployee = departmentEmployee;
        this.location = location;
        this.assigned = assigned;
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

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public int getWorkDay() {
        return workDay;
    }

    public void setWorkDay(int workDay) {
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

	public boolean getAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (assigned ? 1231 : 1237);
		result = prime * result + ((departmentEmployee == null) ? 0 : departmentEmployee.hashCode());
		result = prime * result + ((endHour == null) ? 0 : endHour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startHour == null) ? 0 : startHour.hashCode());
		result = prime * result + workDay;
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
		ShiftVo other = (ShiftVo) obj;
		if (assigned != other.assigned)
			return false;
		if (departmentEmployee == null) {
			if (other.departmentEmployee != null)
				return false;
		} else if (!departmentEmployee.equals(other.departmentEmployee))
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
		if (startHour == null) {
			if (other.startHour != null)
				return false;
		} else if (!startHour.equals(other.startHour))
			return false;
		if (workDay != other.workDay)
			return false;
		return true;
	}
}
