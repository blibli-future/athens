package com.blibli.future.service;
import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.vo.EmployeeResponseVo;
import com.blibli.future.vo.EmployeeRequestVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Autowired
    public EmployeeServiceImpl (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee (EmployeeRequestVo employeeVo){
        if(isEmployeeExist(employeeVo.getNik())){
            return null;
        }else{
            Employee emp = new Employee(employeeVo.getNik(), employeeVo.getFullName(), Gender.valueOf(employeeVo.getGender()), employeeVo.getPosition(), 
            		employeeVo.getLevel(), employeeVo.getOrganizationalUnitText(), MaritalStatus.valueOf(employeeVo.getMaritalStatus()), Religion.valueOf(employeeVo.getReligion()), employeeVo.getNameOfDept(),
            		employeeVo.getChiefNik(), LocalDate.parse(employeeVo.getStartWorkingDate(), formatter), employeeVo.getStatus());
            employeeRepository.save(emp);
            return emp;
        }
    }
    public boolean isEmployeeExist(String nik){
        if (employeeRepository.findOneByNik(nik) !=null){
            return true;
        }
        return  false;
    }


    public Employee updateEmployee (EmployeeRequestVo employeeVo){
    		Employee oldEmployee = employeeRepository.findOneByNik(employeeVo.getNik());
    	if(oldEmployee!=null){
    	   oldEmployee.setChiefNik(employeeVo.getChiefNik());
    	   oldEmployee.setFullName(employeeVo.getFullName());
    	   oldEmployee.setGender(Gender.valueOf(employeeVo.getGender()));
    	   oldEmployee.setLevel(employeeVo.getLevel());
    	   oldEmployee.setMaritalStatus(MaritalStatus.valueOf(employeeVo.getMaritalStatus()));
    	   oldEmployee.setNameOfDept(employeeVo.getNameOfDept());
    	   oldEmployee.setOrganizationalUnitText(employeeVo.getOrganizationalUnitText());
    	   oldEmployee.setReligion(Religion.valueOf(employeeVo.getReligion()));
    	   oldEmployee.setStartWorkingDate(LocalDate.parse(employeeVo.getStartWorkingDate(), formatter));
    	   oldEmployee.setStatus(employeeVo.getStatus());
           employeeRepository.save(oldEmployee);
           return oldEmployee;
       }
       return null;
    }


    public List<Employee> getEmployeesByDept(String nameOfDept){
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeRepository.findByNameOfDept(nameOfDept);
        if(listEmployee!=null){
            return listEmployee;
        }
        return null;
    }
    
	@Override
	public List<EmployeeResponseVo> getAllEmployees() {
		List<EmployeeResponseVo> listEmployee = new ArrayList<>();
        listEmployee = employeeRepository.findAllEmployee();
        if(listEmployee!=null){
            return listEmployee;
        }
        return null;
	}

}
