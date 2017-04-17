package com.blibli.future.service;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeShift;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.service.api.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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



    public boolean saveEmployee (Employee employee){
        if( employee!=null){
            employeeRepository.save(employee);
            return true;
        }
        return false;


    }
    public boolean isEmployeeExist(String nik){
        if (employeeRepository.findOneByNik(nik) !=null){
            return true;
        }
        return  false;
    }


    public boolean updateEmployee (Employee employee){
        Employee oldEmployee = employeeRepository.findOneByNik(employee.getNik());
        oldEmployee.setPosition(employee.getPosition());
        oldEmployee.setFullName(employee.getFullName());
        oldEmployee.setChiefName(employee.getChiefName());
        oldEmployee.setChiefNik(employee.getChiefNik());
        oldEmployee.setChiefPosition(employee.getChiefPosition());
        oldEmployee.setChiefPositionText(employee.getChiefPositionText());
        oldEmployee.setEndWorkingDate(employee.getEndWorkingDate());
        oldEmployee.setGender(employee.getGender());
        oldEmployee.setLevel(employee.getLevel());
        oldEmployee.setMaritalStatus(employee.getMaritalStatus());
        oldEmployee.setOrganizationalUnitText(employee.getOrganizationalUnitText());
        oldEmployee.setReligion(employee.getReligion());
        oldEmployee.setStartWorkingDate(employee.getStartWorkingDate());
        oldEmployee.setNameOfDept(employee.getNameOfDept());
        oldEmployee.setStatus(employee.getStatus());

       if( employee!=null){
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
