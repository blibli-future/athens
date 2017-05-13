package com.blibli.future.enums;

public enum Status {
	WAITING("Waiting"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
