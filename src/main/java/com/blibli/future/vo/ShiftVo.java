package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftVo implements Serializable {
    private static final long serialVersionUID = -8970572276523255465L;
    private String id;
    private String name;
    private String startHour;
    private String endHour;
    private int startDay;
    private int endDay;
    private String department;
    private String location;

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

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

        if(startDay != shiftVo.startDay) return false;
        if(endDay != shiftVo.endDay) return false;
        if(id != null ? !id.equals(shiftVo.id) : shiftVo.id != null) return false;
        if(name != null ? !name.equals(shiftVo.name) : shiftVo.name != null) return false;
        if(startHour != null ? !startHour.equals(shiftVo.startHour) : shiftVo.startHour != null) return false;
        if(endHour != null ? !endHour.equals(shiftVo.endHour) : shiftVo.endHour != null) return false;
        if(department != null ? !department.equals(shiftVo.department) : shiftVo.department != null) return false;
        return location != null ? location.equals(shiftVo.location) : shiftVo.location == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startHour != null ? startHour.hashCode() : 0);
        result = 31 * result + (endHour != null ? endHour.hashCode() : 0);
        result = 31 * result + startDay;
        result = 31 * result + endDay;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}