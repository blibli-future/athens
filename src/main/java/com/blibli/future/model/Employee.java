/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blibli.future.model;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author Thea
 */

@Entity
public class Employee {
    @Id
    private String nik;
    
    private String fullName;
    private Gender gender;
    private String position;
    private String level;
    private String organizationalUnitText;
    private MaritalStatus maritalStatus;
    private Religion religion;
    private String nameOfDept;
    private String chiefNik;
    private String chiefName;
    private String chiefPosition;
    private String chiefPositionText;
    private LocalDate startWorkingDate;
    private LocalDate endWorkingDate;
    private Boolean status;
    @ManyToMany()
    @JoinTable
    private Set<Shift> shifts;
    
    @OneToMany(mappedBy = "employee")
    private Set<EmployeeAbsencePermit> employeeAbsencesPermit;

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeLeave> employeeLeave;
    
    public Employee() {}

    public Employee(String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
                    MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
                    String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status) {
        this.nik = nik;
        this.fullName = fullName;
        this.gender = gender;
        this.position = position;
        this.level = level;
        this.organizationalUnitText = organizationalUnitText;
        this.maritalStatus = maritalStatus;
        this.religion = religion;
        this.nameOfDept = nameOfDept;
        this.chiefNik = chiefNik;
        this.chiefName = chiefName;
        this.chiefPosition = chiefPosition;
        this.chiefPositionText = chiefPositionText;
        this.startWorkingDate = startWorkingDate;
        this.endWorkingDate = endWorkingDate;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrganizationalUnitText() {
        return organizationalUnitText;
    }

    public void setOrganizationalUnitText(String organizationalUnitText) {
        this.organizationalUnitText = organizationalUnitText;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public String getNameOfDept() {
        return nameOfDept;
    }

    public void setNameOfDept(String nameOfDept) {
        this.nameOfDept = nameOfDept;
    }

    public String getChiefNik() {
        return chiefNik;
    }

    public void setChiefNik(String chiefNik) {
        this.chiefNik = chiefNik;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getChiefPosition() {
        return chiefPosition;
    }

    public void setChiefPosition(String chiefPosition) {
        this.chiefPosition = chiefPosition;
    }

    public String getChiefPositionText() {
        return chiefPositionText;
    }

    public void setChiefPositionText(String chiefPositionText) {
        this.chiefPositionText = chiefPositionText;
    }

    public LocalDate getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setStartWorkingDate(LocalDate startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public LocalDate getEndWorkingDate() {
        return endWorkingDate;
    }

    public void setEndWorkingDate(LocalDate endWorkingDate) {
        this.endWorkingDate = endWorkingDate;
    }
    
//    @ManyToMany()
//    @JoinTable(name="employeeShift", joinColumns = @JoinColumn(name="nik", referencedColumnName="nik"), inverseJoinColumns=@JoinColumn(name = "shiftId", referencedColumnName = "id"))
//    public Set<Shift> getShift() {
//        return shifts;
//    }
}
