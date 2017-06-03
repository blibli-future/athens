package com.blibli.future.model;

import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.sql.Date;

import com.blibli.future.enums.Status;

@Entity
public class EmployeeAbsencePermit implements Serializable{
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate requestDate;
	private String reason;
	private Status status;
	@Id
	@ManyToOne()
	@JoinColumn(name = "nik")
	private Employee employee;
	@Id
	@ManyToOne()
	@JoinColumn(name = "idAbsencePermit")
	private AbsencePermit absencePermit;
	
	public EmployeeAbsencePermit(LocalDate startDate, LocalDate endDate, String reason){
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = Status.WAITING;
		this.requestDate = LocalDate.now();
	}

	public EmployeeAbsencePermit() {}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
