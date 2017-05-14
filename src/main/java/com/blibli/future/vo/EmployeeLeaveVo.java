package com.blibli.future.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.blibli.future.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeLeaveVo implements Serializable{
	private static final long serialVersionUID = -4398914737949811884L;
	private String id;
	private String nik;
	private String idLeave;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate requestDate;
	private Status status;
	private String reason;
	
	public EmployeeLeaveVo(String id, String nik, String idLeave, LocalDate startDate, LocalDate endDate, String reason){
		this.id = id;
		this.nik = nik;
		this.idLeave = idLeave;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.requestDate = LocalDate.now();
		this.status = Status.WAITING;
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
	public String getIdLeave() {
		return idLeave;
	}
	public void setIdLeave(String idLeave) {
		this.idLeave = idLeave;
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
