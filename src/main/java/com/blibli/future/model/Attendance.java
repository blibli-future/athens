
package com.blibli.future.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Attendance {
	@Id
	private String nik;
	private Timestamp tapIn;
	private Timestamp tapOut;
	private Date date;
	
	public Attendance(String nik, Timestamp tapIn, Timestamp tapOut,Date date){
		this.nik=nik;
		this.tapIn=tapIn;
		this.tapOut=tapOut;
		this.date=date;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public Timestamp getTapIn() {
		return tapIn;
	}

	public void setTapIn(Timestamp tapIn) {
		this.tapIn = tapIn;
	}

	public Timestamp getTapOut() {
		return tapOut;
	}

	public void setTapOut(Timestamp tapOut) {
		this.tapOut = tapOut;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
