
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

    public Attendance(String nik, LocalDate date, LocalTime tapIn) {
        this.nik = nik;
        this.date = date;
        this.tapIn = tapIn;
        this.tapOut = null;
    }

    public void assign(LocalTime tapTime) {
        if(tapTime.isBefore(this.getTapIn())) {
            this.setTapOut(this.getTapIn());
            this.setTapIn(tapTime);
        } else if(this.getTapOut() == null){
            this.setTapOut(tapTime);
        } else if(tapTime.isAfter(this.getTapOut())) {
            this.setTapOut(tapTime);
        }
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Attendance that = (Attendance) o;

        if(nik != null ? !nik.equals(that.nik) : that.nik != null) return false;
        if(date != null ? !date.equals(that.date) : that.date != null) return false;
        if(tapIn != null ? !tapIn.equals(that.tapIn) : that.tapIn != null) return false;
        return tapOut != null ? tapOut.equals(that.tapOut) : that.tapOut == null;
    }

    @Override
    public int hashCode() {
        int result = nik != null ? nik.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (tapIn != null ? tapIn.hashCode() : 0);
        result = 31 * result + (tapOut != null ? tapOut.hashCode() : 0);
        return result;
    }
}
