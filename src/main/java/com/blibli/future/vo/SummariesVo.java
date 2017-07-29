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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        SummariesVo that = (SummariesVo) o;

        if(yearlyLeaveUsed != that.yearlyLeaveUsed) return false;
        if(substitutionLeaveRightUsed != that.substitutionLeaveRightUsed) return false;
        if(lateUsed != that.lateUsed) return false;
        if(maxYearlyLeave != that.maxYearlyLeave) return false;
        if(maxSubstitutionLeaveRight != that.maxSubstitutionLeaveRight) return false;
        if(maxEmployeeLate != that.maxEmployeeLate) return false;
        return nik.equals(that.nik);
    }

    @Override
    public int hashCode() {
        int result = nik.hashCode();
        result = 31 * result + (int) (yearlyLeaveUsed ^ (yearlyLeaveUsed >>> 32));
        result = 31 * result + (int) (substitutionLeaveRightUsed ^ (substitutionLeaveRightUsed >>> 32));
        result = 31 * result + (int) (lateUsed ^ (lateUsed >>> 32));
        result = 31 * result + maxYearlyLeave;
        result = 31 * result + maxSubstitutionLeaveRight;
        result = 31 * result + maxEmployeeLate;
        return result;
    }
}
