package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by amesa on 5/14/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceKeyVo implements Serializable  {

    public AttendanceKeyVo(String nik, LocalDate date) {
        this.nik = nik;
        this.date = date;
    }

    private String nik;
    private LocalDate date;

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
