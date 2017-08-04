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

import com.blibli.future.enums.Status;
import com.blibli.future.vo.EmployeeLeaveVo;

@Entity
public class EmployeeLeave implements Serializable{
    private String id;
	private Employee employee;
	private Leave leave;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate requestDate;
	private Status status;
	private String reason;
	private String processedBy;
	private LocalDate processedDate;
	
	public EmployeeLeave(Employee employee, Leave leave, LocalDate startDate, LocalDate endDate, String reason){
		this.employee = employee;
		this.leave = leave;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.requestDate = LocalDate.now();
		this.status = Status.WAITING;
		this.processedBy = null;
		this.processedDate = null;
	}

	public EmployeeLeave() {}
	
	public void updateEmployeeLeave(EmployeeLeaveVo empLeaVo, Leave leave){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.startDate = LocalDate.parse(empLeaVo.getStartDate(), formatter);
		this.endDate = LocalDate.parse(empLeaVo.getEndDate(), formatter);
		this.reason = empLeaVo.getReason();
		this.status = empLeaVo.getStatus();
		this.leave = leave;
	}
	
	public static EmployeeLeave convertToEmployeeLeave(EmployeeLeaveVo empLeaVo, Employee emp, Leave lea){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return new EmployeeLeave(emp, lea, LocalDate.parse(empLeaVo.getStartDate(), formatter), LocalDate.parse(empLeaVo.getEndDate(), formatter), empLeaVo.getReason());
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

	@ManyToOne()
	@JoinColumn()
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne()
	@JoinColumn()
	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
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
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((leave == null) ? 0 : leave.hashCode());
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
		EmployeeLeave other = (EmployeeLeave) obj;
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
		if (leave == null) {
			if (other.leave != null)
				return false;
		} else if (!leave.equals(other.leave))
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
