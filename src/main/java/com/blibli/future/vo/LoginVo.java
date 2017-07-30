package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by amesa on 5/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginVo implements Serializable {
	private static final long serialVersionUID = 8835070050119198236L;
	private String nik;
    private String password;
    private String idRole;

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

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
}
