package com.blibli.future.enums;

public enum LateCondition {
    LATE("Late"),
    NOTLATE("Not Late"),
    LATEWITHPERMISSION("Late With Permission");

    private String name;

    LateCondition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
