package com.blibli.future.service;

import com.blibli.future.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amesa on 3/21/17.
 */

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void deleteEmployee(String nik){
        employeeRepository.delete(nik);
    }
}
