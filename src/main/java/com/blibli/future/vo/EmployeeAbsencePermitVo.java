package com.blibli.future.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.blibli.future.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeAbsencePermitVo implements Serializable {
	private static final long serialVersionUID = -4521810996465893249L;
	private String id;
	private String nik;
	private String idAbsencePermit;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate requestDate;
	private String reason;
	private Status status;
	
	public EmployeeAbsencePermitVo(String nik, String idAbsencePermit, 
			LocalDate startDate, LocalDate endDate, String reason){
		this.nik = nik;
		this.idAbsencePermit = idAbsencePermit;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = Status.WAITING;
		this.requestDate = LocalDate.now();
	}
	
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
	public String getIdAbsencePermit() {
		return idAbsencePermit;
	}
	public void setIdAbsencePermit(String idAbsencePermit) {
		this.idAbsencePermit = idAbsencePermit;
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
	
	
}
