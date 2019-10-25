package com.blibli.future.enums;

public enum Status {
	WAITING("waiting"),
    APPROVED("approved"),
    REJECTED("rejected");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
