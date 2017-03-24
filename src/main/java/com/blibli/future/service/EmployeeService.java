package com.blibli.future.service;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by amesa on 3/21/17.
 */

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void deleteEmployee(String nik){
        employeeRepository.delete(nik);
    }

    public void saveEmployee (Employee employee){
        employeeRepository.save(employee);

    }
    public boolean isEmployeeExist(String nik){
        if (employeeRepository.findOneByNik(nik) !=null){
            return true;
        }
        return  false;
    }

    public void updateEmployee (Employee employee){
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

        employeeRepository.save(oldEmployee);


    }

}
