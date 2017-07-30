package com.blibli.future.enums;

/**
 * Created by amesa on 5/6/17.
 */
public enum MaritalStatus {

    MENIKAH("Menikah"),
    LAJANG("Lajang"),
    JANDA("Janda");
    private String status;

    MaritalStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
