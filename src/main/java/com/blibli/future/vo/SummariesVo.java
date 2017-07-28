package com.blibli.future.vo;


public class SummariesVo {
    private String nik;
    private long yearlyLeaveUsed;
    private long substitutionLeaveRightUsed;
    private long lateUsed;
    private int maxYearlyLeave;
    private int maxSubstitutionLeaveRight;
    private int maxEmployeeLate;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public long getYearlyLeaveUsed() {
        return yearlyLeaveUsed;
    }

    public void setYearlyLeaveUsed(long yearlyLeaveUsed) {
        this.yearlyLeaveUsed = yearlyLeaveUsed;
    }

    public long getSubstitutionLeaveRightUsed() {
        return substitutionLeaveRightUsed;
    }

    public void setSubstitutionLeaveRightUsed(long substitutionLeaveRightUsed) {
        this.substitutionLeaveRightUsed = substitutionLeaveRightUsed;
    }

    public long getLateUsed() {
        return lateUsed;
    }

    public void setLateUsed(long lateUsed) {
        this.lateUsed = lateUsed;
    }

    public int getMaxYearlyLeave() {
        return maxYearlyLeave;
    }

    public void setMaxYearlyLeave(int maxYearlyLeave) {
        this.maxYearlyLeave = maxYearlyLeave;
    }

    public int getMaxSubstitutionLeaveRight() {
        return maxSubstitutionLeaveRight;
    }

    public void setMaxSubstitutionLeaveRight(int maxSubstitutionLeaveRight) {
        this.maxSubstitutionLeaveRight = maxSubstitutionLeaveRight;
    }

    public int getMaxEmployeeLate() {
        return maxEmployeeLate;
    }

    public void setMaxEmployeeLate(int maxEmployeeLate) {
        this.maxEmployeeLate = maxEmployeeLate;
    }
}
