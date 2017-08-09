package com.blibli.future.vo.request;

public class EditAttendanceRequest {
    private String nik;
    private String tapTime;
    private String tapDate;
    private String type;

    public EditAttendanceRequest() {
    }

    public EditAttendanceRequest(String nik, String tapTime, String tapDate, String type) {
        this.nik = nik;
        this.tapTime = tapTime;
        this.tapDate = tapDate;
        this.type = type;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTapTime() {
        return tapTime;
    }

    public void setTapTime(String tapTime) {
        this.tapTime = tapTime;
    }

    public String getTapDate() {
        return tapDate;
    }

    public void setTapDate(String tapDate) {
        this.tapDate = tapDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        EditAttendanceRequest that = (EditAttendanceRequest) o;

        if(nik != null ? !nik.equals(that.nik) : that.nik != null) return false;
        if(tapTime != null ? !tapTime.equals(that.tapTime) : that.tapTime != null) return false;
        if(tapDate != null ? !tapDate.equals(that.tapDate) : that.tapDate != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = nik != null ? nik.hashCode() : 0;
        result = 31 * result + (tapTime != null ? tapTime.hashCode() : 0);
        result = 31 * result + (tapDate != null ? tapDate.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
