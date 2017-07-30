package com.blibli.future.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovalRequestVo implements Serializable {
	private static final long serialVersionUID = -2304624049484796493L;
	private String nik;
	private boolean isApproved;
	
	public ApprovalRequestVo(String nik, boolean isApproved){
		this.nik = nik;
		this.isApproved = isApproved;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isApproved ? 1231 : 1237);
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
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
		ApprovalRequestVo other = (ApprovalRequestVo) obj;
		if (isApproved != other.isApproved)
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		return true;
	}
}
