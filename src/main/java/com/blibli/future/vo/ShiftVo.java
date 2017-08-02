package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftVo {
    private String id;
    private String name;
    private int startHour;
    private int endHour;
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

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
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

        if(startHour != shiftVo.startHour) return false;
        if(endHour != shiftVo.endHour) return false;
        if(startDay != shiftVo.startDay) return false;
        if(endDay != shiftVo.endDay) return false;
        if(!id.equals(shiftVo.id)) return false;
        if(!name.equals(shiftVo.name)) return false;
        if(!department.equals(shiftVo.department)) return false;
        return location.equals(shiftVo.location);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + startHour;
        result = 31 * result + endHour;
        result = 31 * result + startDay;
        result = 31 * result + endDay;
        result = 31 * result + department.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }
}