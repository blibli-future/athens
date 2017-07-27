package com.blibli.future.vo;


public class SummariesVo {
    private String nik;
    private long yearlyLeaveCount;
    private long substitutionLeaveRightCount;
    private long employeeLateCount;
    private int maxYearlyLeaveCount;
    private int masSubstitutionLeaveRightCount;
    private int maxEmployeeLateCount;

    public SummariesVo(String nik, Long yearlyLeaveCount, Long substitutionLeaveRightCount, Long employeeLateCount, int maxYearlyLeaveCount, int masSubstitutionLeaveRightCount, int maxEmployeeLateCount) {
        this.nik = nik;
        this.yearlyLeaveCount = yearlyLeaveCount;
        this.substitutionLeaveRightCount = substitutionLeaveRightCount;
        this.employeeLateCount = employeeLateCount;
        this.maxYearlyLeaveCount = maxYearlyLeaveCount;
        this.masSubstitutionLeaveRightCount = masSubstitutionLeaveRightCount;
        this.maxEmployeeLateCount = maxEmployeeLateCount;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public long getYearlyLeaveCount() {
        return yearlyLeaveCount;
    }

    public void setYearlyLeaveCount(long yearlyLeaveCount) {
        this.yearlyLeaveCount = yearlyLeaveCount;
    }

    public long getSubstitutionLeaveRightCount() {
        return substitutionLeaveRightCount;
    }

    public void setSubstitutionLeaveRightCount(long substitutionLeaveRightCount) {
        this.substitutionLeaveRightCount = substitutionLeaveRightCount;
    }

    public long getEmployeeLateCount() {
        return employeeLateCount;
    }

    public void setEmployeeLateCount(long employeeLateCount) {
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
