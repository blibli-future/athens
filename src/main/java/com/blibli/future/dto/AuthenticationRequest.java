package com.blibli.future.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationRequest {

    private String nik;
    private String password;

    public AuthenticationRequest() {}

    public AuthenticationRequest(String nik, String password) {
        this.nik = nik;
        this.password = password;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
