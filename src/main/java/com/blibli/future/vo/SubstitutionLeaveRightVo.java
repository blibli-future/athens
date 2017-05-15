package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by amesa on 5/14/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubstitutionLeaveRightVo implements Serializable {
	private static final long serialVersionUID = -2633999422402173206L;
	private String id;
    private String nik;
    private LocalDate createdDate;
    private LocalDate expiredDate;

    public SubstitutionLeaveRightVo(String id, String nik, LocalDate createdDate, LocalDate expiredDate) {
        this.id = id;
        this.nik = nik;
        this.createdDate = createdDate;
        this.expiredDate = expiredDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}
