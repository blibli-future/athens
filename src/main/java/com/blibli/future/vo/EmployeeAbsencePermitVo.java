package com.blibli.future.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.Status;
import com.blibli.future.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeAbsencePermitVo implements Serializable {
	private static final long serialVersionUID = -4521810996465893249L;
	private String id;
	private String nik;
	private String startDate;
	private String endDate;
	private String reason;
	private Status status;
	private AbsencePermit absencePermit;
	
	public EmployeeAbsencePermitVo(String id, String nik, AbsencePermit absencePermit, 
			String startDate, String endDate, String reason){
		this.id = id;
		this.nik = nik;
		this.absencePermit = absencePermit;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = Status.WAITING;
	}

	public EmployeeAbsencePermitVo(){}
	
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AbsencePermit getAbsencePermit() {
		return absencePermit;
	}

	public void setAbsencePermit(AbsencePermit absencePermit) {
		this.absencePermit = absencePermit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((absencePermit == null) ? 0 : absencePermit.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		EmployeeAbsencePermitVo other = (EmployeeAbsencePermitVo) obj;
		if (absencePermit != other.absencePermit)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
