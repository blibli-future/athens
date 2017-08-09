package com.blibli.future.vo;

import com.blibli.future.model.primaryKey.AttendanceKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceVo implements Serializable {
	private static final long serialVersionUID = -1488649585498344022L;

	public AttendanceVo(AttendanceKey attendanceKey, LocalTime tapIn, LocalTime tapOut, String nik, LocalDate date) {
        this.attendanceKey = attendanceKey;
        this.tapIn = tapIn;
        this.tapOut = tapOut;
        this.nik = nik;
        this.date = date;
    }

    private AttendanceKey attendanceKey;
    private LocalTime tapIn;
    private LocalTime tapOut;
    private String nik;
    private LocalDate date;

    public AttendanceKey getAttendanceKey() {
        return attendanceKey;
    }

    public void setAttendanceKey(AttendanceKey attendanceKey) {
        this.attendanceKey = attendanceKey;
    }

    public LocalTime getTapIn() {
        return tapIn;
    }

    public void setTapIn(LocalTime tapIn) {
        this.tapIn = tapIn;
    }

    public LocalTime getTapOut() {
        return tapOut;
    }

    public void setTapOut(LocalTime tapOut) {
        this.tapOut = tapOut;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
