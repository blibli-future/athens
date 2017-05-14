package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by amesa on 5/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftVo implements Serializable {
    private String id;
    private String name;
    private Timestamp startHour;
    private Timestamp endHour;
    private String startDay;
    private String endDay;
    private String departmentEmployee;
    private String location;

    public ShiftVo(String id, String name, Timestamp startHour, Timestamp endHour, String startDay, String endDay, String departmentEmployee, String location) {
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

    public Timestamp getStartHour() {
        return startHour;
    }

    public void setStartHour(Timestamp startHour) {
        this.startHour = startHour;
    }

    public Timestamp getEndHour() {
        return endHour;
    }

    public void setEndHour(Timestamp endHour) {
        this.endHour = endHour;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
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
