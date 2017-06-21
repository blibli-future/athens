package com.blibli.future.service;
import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.service.api.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amesa on 3/21/17.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }



    public boolean saveEmployee (String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
                                 MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
                                 String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status){
        if(isEmployeeExist(nik)){
            return false;
        }else{
            Employee emp = new Employee(nik, fullName,gender,position,level,
                    organizationalUnitText,maritalStatus,religion,nameOfDept,chiefNik,
                    chiefName,chiefPosition,chiefPositionText,
                    startWorkingDate,endWorkingDate,status);
            employeeRepository.save(emp);
            return true;
        }


    }
    public boolean isEmployeeExist(String nik){
        if (employeeRepository.findOneByNik(nik) !=null){
            return true;
        }
        return  false;
    }


    public boolean updateEmployee (String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
            MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
            String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status){
    		Employee oldEmployee = employeeRepository.findOneByNik(nik);
    	if(oldEmployee!=null){
    	   oldEmployee.setChiefName(chiefName);
    	   oldEmployee.setChiefNik(chiefNik);
    	   oldEmployee.setChiefPosition(chiefPosition);
    	   oldEmployee.setChiefPositionText(chiefPositionText);
    	   oldEmployee.setEndWorkingDate(endWorkingDate);
    	   oldEmployee.setFullName(fullName);
    	   oldEmployee.setGender(gender);
    	   oldEmployee.setLevel(level);
    	   oldEmployee.setMaritalStatus(maritalStatus);
    	   oldEmployee.setNameOfDept(nameOfDept);
    	   oldEmployee.setOrganizationalUnitText(organizationalUnitText);
    	   oldEmployee.setReligion(religion);
    	   oldEmployee.setStartWorkingDate(startWorkingDate);
    	   oldEmployee.setStatus(status);
           employeeRepository.save(oldEmployee);
           return true;
       }
       return false;
    }


    public List<Employee> getEmployeesByDept(String nameOfDept){
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeRepository.findByNameOfDept(nameOfDept);
        if(listEmployee!=null){
            return listEmployee;
        }
        return null;

    }

}
