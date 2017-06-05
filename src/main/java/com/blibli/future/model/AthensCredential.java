package com.blibli.future.model;

import com.blibli.future.enums.Role;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class AthensCredential {
    @Id
    private String email;
    private String nik;
    @ElementCollection
    private Set<Role> roles;

    public AthensCredential() {
    }

    public AthensCredential(String email, String nik, Set<Role> roles) {
        this.email = email;
        this.nik = nik;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
