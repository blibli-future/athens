package com.blibli.future.model.primaryKey;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class AttendanceKey implements Serializable {
    private String nik;
    private LocalDate date;

    public AttendanceKey() {}

    public AttendanceKey(String nik, LocalDate date) {
        this.nik = nik;
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        AttendanceKey that = (AttendanceKey) o;

        if(!nik.equals(that.nik)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = nik.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
