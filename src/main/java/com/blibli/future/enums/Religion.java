package com.blibli.future.enums;

/**
 * Created by amesa on 5/6/17.
 */
public enum Religion {
    KRISTEN("Kristen"),
    KATHOLIK("Katholik"),
    ISLAM("Islam"),
    BUDHA("Budha"),
    MUSLIM("Muslim");
    private String name;

    Religion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
