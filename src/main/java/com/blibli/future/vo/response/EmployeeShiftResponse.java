package com.blibli.future.vo.response;

import com.blibli.future.model.Employee;

public class EmployeeShiftResponse {
    private String nik;
    private String fullName;
    private String position;
    private String organizationalUnitText;
    private String department;

    public EmployeeShiftResponse() {
    }

    public EmployeeShiftResponse(Employee employee) {
        this.nik = employee.getNik();
        this.fullName = employee.getFullName();
        this.position = employee.getPosition();
        this.organizationalUnitText = employee.getOrganizationalUnitText();
        this.department = employee.getNameOfDept();
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganizationalUnitText() {
        return organizationalUnitText;
    }

    public void setOrganizationalUnitText(String organizationalUnitText) {
        this.organizationalUnitText = organizationalUnitText;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        EmployeeShiftResponse that = (EmployeeShiftResponse) o;

        if(nik != null ? !nik.equals(that.nik) : that.nik != null) return false;
        if(fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if(position != null ? !position.equals(that.position) : that.position != null) return false;
        if(organizationalUnitText != null ? !organizationalUnitText.equals(that.organizationalUnitText) : that.organizationalUnitText != null)
            return false;
        return department != null ? department.equals(that.department) : that.department == null;
    }

    @Override
    public int hashCode() {
        int result = nik != null ? nik.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (organizationalUnitText != null ? organizationalUnitText.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }
}
