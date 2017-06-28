package com.blibli.future.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class YearlyLeave {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nik;
	private LocalDate startDate;
	private LocalDate endDate;
	private int hakCuti;
	private LocalDate deductionStart;
	private LocalDate deductionEnd;
	
	public YearlyLeave(){}

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

	public int getHakCuti() {
		return hakCuti;
	}

	public void setHakCuti(int hakCuti) {
		this.hakCuti = hakCuti;
	}

	public LocalDate getDeductionStart() {
		return deductionStart;
	}

	public void setDeductionStart(LocalDate deductionStart) {
		this.deductionStart = deductionStart;
	}

	public LocalDate getDeductionEnd() {
		return deductionEnd;
	}

	public void setDeductionEnd(LocalDate deductionEnd) {
		this.deductionEnd = deductionEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deductionEnd == null) ? 0 : deductionEnd.hashCode());
		result = prime * result + ((deductionStart == null) ? 0 : deductionStart.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + hakCuti;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		YearlyLeave other = (YearlyLeave) obj;
		if (deductionEnd == null) {
			if (other.deductionEnd != null)
				return false;
		} else if (!deductionEnd.equals(other.deductionEnd))
			return false;
		if (deductionStart == null) {
			if (other.deductionStart != null)
				return false;
		} else if (!deductionStart.equals(other.deductionStart))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (hakCuti != other.hakCuti)
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
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
