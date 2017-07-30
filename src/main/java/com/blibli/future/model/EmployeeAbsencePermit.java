package com.blibli.future.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.Status;
import com.blibli.future.vo.EmployeeAbsencePermitVo;

@Entity

public class EmployeeAbsencePermit implements Serializable{
	private String id;
	private Employee employee;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate requestDate;
	private String reason;
	private Status status;
	private AbsencePermit absencePermit;
	private String processedBy;
	private LocalDate processedDate;
	
	public EmployeeAbsencePermit(){}
	
	public EmployeeAbsencePermit(Employee employee, AbsencePermit absencePermit,LocalDate startDate, LocalDate endDate, String reason){
		this.employee = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = Status.WAITING;
		this.requestDate = LocalDate.now();
		this.absencePermit = absencePermit;
		this.processedBy = null;
		this.processedDate = null;
	}
	
	public void updateEmployeeAbsencePermit(EmployeeAbsencePermitVo employeeAbsencePermitVo){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.startDate = LocalDate.parse(employeeAbsencePermitVo.getStartDate(), formatter);
		this.endDate = LocalDate.parse(employeeAbsencePermitVo.getEndDate(), formatter);
		this.reason = employeeAbsencePermitVo.getReason();
		this.status = employeeAbsencePermitVo.getStatus();
		this.absencePermit = employeeAbsencePermitVo.getAbsencePermit();
	}
	
	public static EmployeeAbsencePermit convertToEmployeeAbsencePermit(EmployeeAbsencePermitVo empAbsPerVo, Employee emp){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return new EmployeeAbsencePermit(emp, empAbsPerVo.getAbsencePermit(), LocalDate.parse(empAbsPerVo.getStartDate(), formatter), LocalDate.parse(empAbsPerVo.getEndDate(), formatter), empAbsPerVo.getReason());
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid", strategy = "uuid2")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name = "employee_nik")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
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

	public String getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public LocalDate getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(LocalDate processedDate) {
		this.processedDate = processedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((absencePermit == null) ? 0 : absencePermit.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((processedBy == null) ? 0 : processedBy.hashCode());
		result = prime * result + ((processedDate == null) ? 0 : processedDate.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
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
		EmployeeAbsencePermit other = (EmployeeAbsencePermit) obj;
		if (absencePermit != other.absencePermit)
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
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
		if (processedBy == null) {
			if (other.processedBy != null)
				return false;
		} else if (!processedBy.equals(other.processedBy))
			return false;
		if (processedDate == null) {
			if (other.processedDate != null)
				return false;
		} else if (!processedDate.equals(other.processedDate))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
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
