package com.blibli.future.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.blibli.future.enums.LateCondition;
import static java.time.temporal.ChronoUnit.MINUTES;
import com.blibli.future.model.primaryKey.AttendanceKey;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Attendance {
	@EmbeddedId
    private AttendanceKey attendanceKey;
	private LocalTime tapIn;
	private LocalTime tapOut;
	private double earlyLeaveHour;
	private LateCondition lateCondition;

    public Attendance() {}

    //TODO: IMPLEMENT TAP TIME LOGIC
    public Attendance(String nik, LocalDate date, LocalTime tap) {
    	this.attendanceKey = new AttendanceKey(nik, date);
        this.tapIn = tap;
        this.tapOut = null;
        this.lateCondition = null;
    }

    public Attendance(String nik, LocalDate date, LocalTime tapIn, LocalTime tapOut) {
        this.attendanceKey = new AttendanceKey(nik, date);
        this.tapIn = tapIn;
        this.tapOut = tapOut;
        this.earlyLeaveHour = measureEarlyLeave(tapIn, tapOut);
        this.lateCondition = null;
    }
    
    private double measureEarlyLeave(LocalTime tapIn, LocalTime tapOut){
    	double diff = tapIn.until(tapOut, MINUTES);
    	if(diff>0)
    		return diff;
    	else
    		return 0;
    }

    public void assign(LocalTime tapTime) {
        if(this.getTapOut() == null) {
            if(tapTime.isBefore(this.tapIn)) {
                this.tapOut = tapIn;
                this.tapIn = tapTime;
            } else {
                this.tapOut = tapTime;
            }
        } else {
            if(tapTime.isBefore(tapIn)) {
                this.tapIn = tapTime;
            } else if(tapTime.isAfter(tapOut)) {
                this.tapOut = tapTime;
            }
        }
        this.earlyLeaveHour = this.measureEarlyLeave(this.tapIn, this.tapOut);
    }

    public String getNik() {
        return this.attendanceKey.getNik();
    }

    public LocalDate getDate() {
        return this.attendanceKey.getDate();
    }

    public LocalTime getTapIn() {
        return tapIn;
    }

    public void setTapIn(LocalTime tapIn) {
        this.tapIn = tapIn;
    }

    public LocalTime getTapOut() {
        return tapOut;
    }

    public void setTapOut(LocalTime tapOut) {
        this.tapOut = tapOut;
    }

    public AttendanceKey getAttendanceKey() {
		return attendanceKey;
	}

	public void setAttendanceKey(AttendanceKey attendanceKey) {
		this.attendanceKey = attendanceKey;
	}

	public double getEarlyLeaveHour() {
		return earlyLeaveHour;
	}

	public void setEarlyLeaveHour(double earlyLeaveHour) {
		this.earlyLeaveHour = earlyLeaveHour;
	}

	public LateCondition getLateCondition() {
		return lateCondition;
	}

	public void setLateCondition(LateCondition lateCondition) {
		this.lateCondition = lateCondition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendanceKey == null) ? 0 : attendanceKey.hashCode());
		long temp;
		temp = Double.doubleToLongBits(earlyLeaveHour);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lateCondition == null) ? 0 : lateCondition.hashCode());
		result = prime * result + ((tapIn == null) ? 0 : tapIn.hashCode());
		result = prime * result + ((tapOut == null) ? 0 : tapOut.hashCode());
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
		Attendance other = (Attendance) obj;
		if (attendanceKey == null) {
			if (other.attendanceKey != null)
				return false;
		} else if (!attendanceKey.equals(other.attendanceKey))
			return false;
		if (Double.doubleToLongBits(earlyLeaveHour) != Double.doubleToLongBits(other.earlyLeaveHour))
			return false;
		if (lateCondition != other.lateCondition)
			return false;
		if (tapIn == null) {
			if (other.tapIn != null)
				return false;
		} else if (!tapIn.equals(other.tapIn))
			return false;
		if (tapOut == null) {
			if (other.tapOut != null)
				return false;
		} else if (!tapOut.equals(other.tapOut))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceKey=" + attendanceKey + ", tapIn=" + tapIn + ", tapOut=" + tapOut
				+ ", earlyLeaveHour=" + earlyLeaveHour + ", lateCondition=" + lateCondition + "]";
	}
}