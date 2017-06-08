package com.blibli.future.model;

import com.blibli.future.enums.Role;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class AthensCredential {
    @Id
    private String username;
    private String password;
    private String nik;
    @ElementCollection
    private Set<Role> roles;

    public AthensCredential() {
    }

    public AthensCredential(String username, String password, String nik, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.nik = nik;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
