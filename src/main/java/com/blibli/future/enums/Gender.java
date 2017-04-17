package com.blibli.future.enums;


public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public Gender parse(String gender){
    	if(gender.equalsIgnoreCase("MALE"))
    	{
    		return MALE;
    	}
    	else if(gender.equalsIgnoreCase("FEMALE")){
    		return FEMALE;
    	}
    	return null;
    }
}

