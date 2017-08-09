package com.blibli.future.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class TapData{
    private String nik;
    private LocalTime tapTime;
    private LocalDate tapDate;

    public TapData() {
    }

    public TapData(String nik, LocalTime tapTime, LocalDate tapDate) {
        this.nik = nik;
        this.tapTime = tapTime;
        this.tapDate = tapDate;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public LocalTime getTapTime() {
        return tapTime;
    }

    public void setTapTime(LocalTime tapTime) {
        this.tapTime = tapTime;
    }

    public LocalDate getTapDate() {
        return tapDate;
    }

    public void setTapDate(LocalDate tapDate) {
        this.tapDate = tapDate;
    }
}
