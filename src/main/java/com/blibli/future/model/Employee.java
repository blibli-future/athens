package com.blibli.future.model;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Employee {
    @Id
    private String nik;
    private String fullName;
    private Gender gender;
    private String position;
    private String level;
    private String organizationalUnitText;
    private MaritalStatus maritalStatus;
    private Religion religion;
    private String nameOfDept;
    private String chiefNik;
    private String chiefName;
    private String chiefPosition;
    private String chiefPositionText;
    private LocalDate startWorkingDate;
    private LocalDate endWorkingDate;
    private Boolean status;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Shift> shifts;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeeAbsencePermit> employeeAbsencePermits;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeeLeave> employeeLeaves;
    
    public Employee() {}

    public Employee(String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
                    MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
                    String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status) {
        this.nik = nik;
        this.fullName = fullName;
        this.gender = gender;
        this.position = position;
        this.level = level;
        this.organizationalUnitText = organizationalUnitText;
        this.maritalStatus = maritalStatus;
        this.religion = religion;
        this.nameOfDept = nameOfDept;
        this.chiefNik = chiefNik;
        this.chiefName = chiefName;
        this.chiefPosition = chiefPosition;
        this.chiefPositionText = chiefPositionText;
        this.startWorkingDate = startWorkingDate;
        this.endWorkingDate = endWorkingDate;
        this.status = status;
        this.employeeAbsencePermits = new HashSet<>();
        this.shifts = new HashSet<>();
        this.employeeLeaves = new HashSet<>();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrganizationalUnitText() {
        return organizationalUnitText;
    }

    public void setOrganizationalUnitText(String organizationalUnitText) {
        this.organizationalUnitText = organizationalUnitText;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public String getNameOfDept() {
        return nameOfDept;
    }

    public void setNameOfDept(String nameOfDept) {
        this.nameOfDept = nameOfDept;
    }

    public String getChiefNik() {
        return chiefNik;
    }

    public void setChiefNik(String chiefNik) {
        this.chiefNik = chiefNik;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getChiefPosition() {
        return chiefPosition;
    }

    public void setChiefPosition(String chiefPosition) {
        this.chiefPosition = chiefPosition;
    }

    public String getChiefPositionText() {
        return chiefPositionText;
    }

    public void setChiefPositionText(String chiefPositionText) {
        this.chiefPositionText = chiefPositionText;
    }

    public LocalDate getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setStartWorkingDate(LocalDate startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public LocalDate getEndWorkingDate() {
        return endWorkingDate;
    }

    public void setEndWorkingDate(LocalDate endWorkingDate) {
        this.endWorkingDate = endWorkingDate;
    }

	public Set<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(Set<Shift> shifts) {
		this.shifts = shifts;
	}

	public void addShifts(Shift shifts) {
		this.shifts.add(shifts);
	}
	
	public Set<EmployeeAbsencePermit> getEmployeeAbsencePermits() {
		return employeeAbsencePermits;
	}

	public void setEmployeeAbsencePermits(Set<EmployeeAbsencePermit> employeeAbsencePermit) {
		this.employeeAbsencePermits = employeeAbsencePermit;
	}
	
	public void addEmployeeAbsencePermits(EmployeeAbsencePermit employeeAbsencePermit) {
		this.employeeAbsencePermits.add(employeeAbsencePermit);
	}
	
	public Set<EmployeeLeave> getEmployeeLeaves() {
		return employeeLeaves;
	}

	public void setEmployeeLeaves(Set<EmployeeLeave> employeeLeave) {
		this.employeeLeaves = employeeLeave;
	}
	
	public void addEmployeeLeaves(EmployeeLeave employeeLeave) {
		this.employeeLeaves.add(employeeLeave);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chiefName == null) ? 0 : chiefName.hashCode());
		result = prime * result + ((chiefNik == null) ? 0 : chiefNik.hashCode());
		result = prime * result + ((chiefPosition == null) ? 0 : chiefPosition.hashCode());
		result = prime * result + ((chiefPositionText == null) ? 0 : chiefPositionText.hashCode());
		result = prime * result + ((employeeAbsencePermits == null) ? 0 : employeeAbsencePermits.hashCode());
		result = prime * result + ((employeeLeaves == null) ? 0 : employeeLeaves.hashCode());
		result = prime * result + ((endWorkingDate == null) ? 0 : endWorkingDate.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((nameOfDept == null) ? 0 : nameOfDept.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result + ((organizationalUnitText == null) ? 0 : organizationalUnitText.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((religion == null) ? 0 : religion.hashCode());
		result = prime * result + ((shifts == null) ? 0 : shifts.hashCode());
		result = prime * result + ((startWorkingDate == null) ? 0 : startWorkingDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (chiefName == null) {
			if (other.chiefName != null)
				return false;
		} else if (!chiefName.equals(other.chiefName))
			return false;
		if (chiefNik == null) {
			if (other.chiefNik != null)
				return false;
		} else if (!chiefNik.equals(other.chiefNik))
			return false;
		if (chiefPosition == null) {
			if (other.chiefPosition != null)
				return false;
		} else if (!chiefPosition.equals(other.chiefPosition))
			return false;
		if (chiefPositionText == null) {
			if (other.chiefPositionText != null)
				return false;
		} else if (!chiefPositionText.equals(other.chiefPositionText))
			return false;
		if (employeeAbsencePermits == null) {
			if (other.employeeAbsencePermits != null)
				return false;
		} else if (!employeeAbsencePermits.equals(other.employeeAbsencePermits))
			return false;
		if (employeeLeaves == null) {
			if (other.employeeLeaves != null)
				return false;
		} else if (!employeeLeaves.equals(other.employeeLeaves))
			return false;
		if (endWorkingDate == null) {
			if (other.endWorkingDate != null)
				return false;
		} else if (!endWorkingDate.equals(other.endWorkingDate))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (gender != other.gender)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (maritalStatus != other.maritalStatus)
			return false;
		if (nameOfDept == null) {
			if (other.nameOfDept != null)
				return false;
		} else if (!nameOfDept.equals(other.nameOfDept))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (organizationalUnitText == null) {
			if (other.organizationalUnitText != null)
				return false;
		} else if (!organizationalUnitText.equals(other.organizationalUnitText))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (religion != other.religion)
			return false;
		if (shifts == null) {
			if (other.shifts != null)
				return false;
		} else if (!shifts.equals(other.shifts))
			return false;
		if (startWorkingDate == null) {
			if (other.startWorkingDate != null)
				return false;
		} else if (!startWorkingDate.equals(other.startWorkingDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
 
}
