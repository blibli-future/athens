package com.blibli.future.model;

import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import javax.persistence.Column;
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
public class EmployeeLeave implements Serializable{
	@Id
	@ManyToOne()
	@JoinColumn(name = "nik")
	private Employee employee;
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "idLeave")
	private Leave leave;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate requestDate;
	private Status status;
	private String reason;
	
	
	
	public EmployeeLeave(LocalDate startDate, LocalDate endDate, String reason){
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.requestDate = LocalDate.now();
		this.status = Status.WAITING;
	}

	public EmployeeLeave() {}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
