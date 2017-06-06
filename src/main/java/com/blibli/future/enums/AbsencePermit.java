package com.blibli.future.enums;

public enum AbsencePermit {
	IJIN1("ijin1"),
    IJIN2("ijin2");

    private String name;
    
    AbsencePermit(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}    
}
