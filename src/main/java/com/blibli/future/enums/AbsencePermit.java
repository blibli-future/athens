package com.blibli.future.enums;

public enum AbsencePermit {
	UNPAID_LEAVE("Unpaid Leave"),
    HOURLY("Hourly"),
    SICK("Sick"),
    SICK_WITH_HOSPITAL_LETTER("Sick With Hospital Letter");

    private String name;
    
    AbsencePermit(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}    
}
