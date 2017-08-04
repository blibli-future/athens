package com.blibli.future.enums;

public enum Religion {
    KRISTEN("Kristen"),
    KATHOLIK("Katholik"),
    ISLAM("Islam"),
    BUDHA("Budha"),
    HINDU("Hindu");
    private String name;

    Religion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
