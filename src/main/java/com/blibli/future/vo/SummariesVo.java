package com.blibli.future.vo;


public class SummariesVo {
    private String nik;
    private int yearlyLeaveCount;
    private int substitutionLeaveRightCount;
    private int employeeLateCount;
    private int maxYearlyLeaveCount;
    private int masSubstitutionLeaveRightCount;
    private int maxEmployeeLateCount;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public int getYearlyLeaveCount() {
        return yearlyLeaveCount;
    }

    public void setYearlyLeaveCount(int yearlyLeaveCount) {
        this.yearlyLeaveCount = yearlyLeaveCount;
    }

    public int getSubstitutionLeaveRightCount() {
        return substitutionLeaveRightCount;
    }

    public void setSubstitutionLeaveRightCount(int substitutionLeaveRightCount) {
        this.substitutionLeaveRightCount = substitutionLeaveRightCount;
    }

    public int getEmployeeLateCount() {
        return employeeLateCount;
    }

    public void setEmployeeLateCount(int employeeLateCount) {
        this.employeeLateCount = employeeLateCount;
    }

    public int getMaxYearlyLeaveCount() {
        return maxYearlyLeaveCount;
    }

    public void setMaxYearlyLeaveCount(int maxYearlyLeaveCount) {
        this.maxYearlyLeaveCount = maxYearlyLeaveCount;
    }

    public int getMasSubstitutionLeaveRightCount() {
        return masSubstitutionLeaveRightCount;
    }

    public void setMasSubstitutionLeaveRightCount(int masSubstitutionLeaveRightCount) {
        this.masSubstitutionLeaveRightCount = masSubstitutionLeaveRightCount;
    }

    public int getMaxEmployeeLateCount() {
        return maxEmployeeLateCount;
    }

    public void setMaxEmployeeLateCount(int maxEmployeeLateCount) {
        this.maxEmployeeLateCount = maxEmployeeLateCount;
    }
}
