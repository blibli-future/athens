package com.blibli.future.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * Created by amesa on 5/14/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbsencePermitVo implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbsencePermitVo(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
