package com.blibli.future.vo;

import com.blibli.future.model.Shift;

public class ShiftVo {
	private String id;
    private String name;
    private String startHour;
    private String endHour;
    private int workDay;
    private String departmentEmployee;
    private String location;

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
    }

    public ShiftVo(String id, String name, String startHour, String endHour, int workDay, String departmentEmployee, String location) {
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ShiftVo shiftVo = (ShiftVo) o;

        if(workDay != shiftVo.workDay) return false;
        if(id != null ? !id.equals(shiftVo.id) : shiftVo.id != null) return false;
        if(name != null ? !name.equals(shiftVo.name) : shiftVo.name != null) return false;
        if(startHour != null ? !startHour.equals(shiftVo.startHour) : shiftVo.startHour != null) return false;
        if(endHour != null ? !endHour.equals(shiftVo.endHour) : shiftVo.endHour != null) return false;
        if(departmentEmployee != null ? !departmentEmployee.equals(shiftVo.departmentEmployee) : shiftVo.departmentEmployee != null)
            return false;
        return location != null ? location.equals(shiftVo.location) : shiftVo.location == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startHour != null ? startHour.hashCode() : 0);
        result = 31 * result + (endHour != null ? endHour.hashCode() : 0);
        result = 31 * result + workDay;
        result = 31 * result + (departmentEmployee != null ? departmentEmployee.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
