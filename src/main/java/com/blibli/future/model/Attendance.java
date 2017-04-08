
package com.blibli.future.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Attendance {
	@Id
	private String nik;
	private LocalDate date;
	private LocalTime tapIn;
	private LocalTime tapOut;

    public Attendance(String nik, LocalDate date, LocalTime tapIn, LocalTime tapOut) {
        this.nik = nik;
        this.date = date;
        this.tapIn = tapIn;
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
}
